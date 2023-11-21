import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class editDistributionInfo extends JPanel implements ActionListener{

    JTable table;
    JButton addButton;
    JPanel editPanel, topPanel, addPanel;
    JLabel label[], addLabel[];
    JTextField textField[], addText[];
    JRadioButton activeButton, addActiveButton;
    FoodDistributionTable pTable = new FoodDistributionTable();
    String DistributionID;
    List<FoodDistribution> allFoodDistribution = new ArrayList<>();
    List<String> linesO;
    List<String[]> info = new ArrayList<>();


    public editDistributionInfo(){

        super(new BorderLayout());


        try {
            linesO = Files.readAllLines(Paths.get("FoodDistributionInfo.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < linesO.size(); i++) {
                String[] items = linesO.get(i).split(";");
                String pptID = items[0];
                    allFoodDistribution.add (new FoodDistribution(pptID, items[1], items[2], items[3], items[4]));
        }
    

        CSV csv = new CSV();
        ArrayList<String[]> ppt = csv.ReadCSVfile();
        pTable.addRowData(ppt);

        table = new JTable();
        this.table.setPreferredScrollableViewportSize(new Dimension(1200, 500));
        this.table.setFillsViewportHeight(false);

        addButton = new JButton("Create");
        addButton.addActionListener(this);
        topPanel =new JPanel(new FlowLayout(FlowLayout.LEADING));
        topPanel.add(addButton);
        add(topPanel, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        table.addMouseListener(new Mouse());


        this.table.setModel(pTable);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        int column = this.table.getColumnCount() - 1; //minus 1 for the edit column

        editPanel = new JPanel();
        editPanel.setLayout(new GridLayout(column,3));

        addPanel = new JPanel();
        addPanel.setLayout(new GridLayout(column,3));
        
        label = new JLabel[column];
        textField = new JTextField[column];

        addLabel = new JLabel[column];
        addText = new JTextField[column];



        for (int k = 0; k < column; k++){
            addLabel[k] = new JLabel(table.getColumnName(k));
            addText[k] = new JTextField(20);
                addPanel.add(addLabel[k]);
                addPanel.add(addText[k]);
            
            if(table.getColumnName(k) == "Distribution ID" ){
                addText[k].setEditable(false); 
            }
            
        }


        for (int i = 0; i < column; i++){
            label[i] = new JLabel(table.getColumnName(i));
            textField[i] = new JTextField(20);
                editPanel.add(label[i]);
                editPanel.add(textField[i]);
            if(table.getColumnName(i) == "Distribution ID"){
               textField[i].setEditable(false);}
           
        }
        


        this.table.setFocusable(true);
        this.table.setRowSelectionAllowed(true);
       this.table.getColumnModel().getColumn(5).setCellRenderer(new LabelCellRenderer());

    }

    class CSV {
        ArrayList<String[]> Property = new ArrayList<String[]>();
        String[] row;
       
        public ArrayList<String[]> ReadCSVfile() {
        ArrayList<FoodDistribution> property = new ArrayList<FoodDistribution>();
        ArrayList<String[]> pptArray = new ArrayList<String[]>();;
        String[] ppt;
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get("FoodDistributionInfo.txt"));
            for (int i = 0; i < lines.size(); i++){
            String[] items = lines.get(i).split(";");
            property.add (new FoodDistribution(items[0], items[1], items[2], items[3], items[4]));
            ppt = new String[]{items[0], items[1], items[2], items[3], items[4],  "Edit/Delete"};
            pptArray.add(ppt);
           
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return pptArray;
        }
    }


    

    @Override
    public void actionPerformed(ActionEvent e) {

        LocalDateTime lDateTime = LocalDateTime.now();
        DateTimeFormatter dtFm = DateTimeFormatter.ofPattern("ddMMyyyyHHmm");
        String strDateTime = dtFm.format(lDateTime);

        addText[0].setText("D" + strDateTime);

        if(e.getSource()==addButton){

            
            FoodDistribution createDistribution = new FoodDistribution();
            int addResult = JOptionPane.showConfirmDialog(editDistributionInfo.this,addPanel,"Create Distribution Info",JOptionPane.OK_CANCEL_OPTION);
        
            if(addResult == JOptionPane.OK_OPTION){
                    try {
                        if(addText[1].getText().length() == 0 ||
                           addText[2].getText().length() == 0 || 
                           addText[3].getText().length() == 0 ||
                           addText[4].getText().length() == 0 ){
                           JOptionPane.showMessageDialog(null, "Creation Failed! \n**Make sure you have filled in every details.");
                           }

                        else 
                            createDistribution.createFoodDistributionInfo(addText[0].getText(), addText[1].getText(), addText[2].getText(), addText[3].getText(), addText[4].getText() );
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

             
                CSV newcsv = new CSV();
                table.setModel(pTable);
                ArrayList<String[]> ppt = newcsv.ReadCSVfile();
                pTable.addRowData(ppt);
                for (int m = 0; m < 6; m++){
                    addText[m].setText("");
                }
            }


        }
        
    }

    class FoodDistributionTable extends AbstractTableModel{

        String[] columns = new String [] { "Distribution ID", "Category","Date","Time", "Location", "Action"};
        ArrayList<String[]> rowData = new ArrayList<String[]>();

        public void addRowData(ArrayList<String[]> rowData) {
            this.rowData = rowData;
            this.fireTableDataChanged();
        }

        @Override
        public int getRowCount() {
            return rowData.size();
        }

        @Override
        public int getColumnCount() {
            return columns.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return rowData.get(rowIndex)[columnIndex];
        }

        @Override
        public String getColumnName(int column){
            return columns[column];
        }

    }

    class Mouse extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent evt){
            int x = evt.getX();
            int y = evt.getY();
            int row = table.rowAtPoint(new Point(x,y));
            int column = table.columnAtPoint(new Point(x,y));

            if (column == 5){
                String pptArray[] = new String[table.getColumnCount() - 1];
                for (int i = 0 ; i < pptArray.length ; i++){
                    pptArray[i] = (String)table.getValueAt(row,i);
                    textField[i].setText(pptArray[i]);

                }
                
                
                Object[] options = {"OK", "Delete","Cancel"};
                int result = JOptionPane.showOptionDialog(editDistributionInfo.this,editPanel, "Edit Distribution Info", 0, JOptionPane.INFORMATION_MESSAGE, null, options, null);

                 FoodDistribution editProperty = new FoodDistribution();

                 if(result == JOptionPane.YES_OPTION){ 
                     try {
                        editProperty.saveAfterEdit(textField[0].getText(),textField);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                CSV newcsv = new CSV();
                table.setModel(pTable);
                ArrayList<String[]> ppt = newcsv.ReadCSVfile();
                pTable.addRowData(ppt);
               }



               else if (result == JOptionPane.NO_OPTION){

                        int confirm = JOptionPane.showConfirmDialog(null, "Confirm to Delete ?");
                        if (confirm == 0) {
                        try {
                            editProperty.deleteFoodDistributionInfo(allFoodDistribution, textField[0].getText());
                            System.out.println(textField[0].getText());

                        }
                        catch (IOException ex) {
                            System.out.println (ex.toString());
                        }   
               // model.removeRow(row);
               // table.changeSelection(row, 3, false, false);  

               CSV newcsv = new CSV();
               table.setModel(pTable);
               ArrayList<String[]> ppt = newcsv.ReadCSVfile();
               pTable.addRowData(ppt);

            }
            }
                
                

              
            }
             
            }
        }

        public class LabelCellRenderer extends DefaultTableCellRenderer {
            public Component getTableCellRendererComponent(JTable table,Object Value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component = super.getTableCellRendererComponent(table,Value,isSelected, hasFocus,row, column);
                String value = (String)Value;
                JLabel edit =(JLabel)component;
                edit.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                edit.setBackground(new Color(255,255,204));
                edit.setHorizontalTextPosition(SwingUtilities.CENTER);
                edit.setHorizontalAlignment(SwingUtilities.CENTER);
                edit.setText(value);
                return edit;
            }
        }
    
}