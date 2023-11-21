import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.table.*;
import javax.swing.event.CellEditorListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReviewRegistration extends JPanel {
    Admin admin = new Admin();
    JButton rejectBtn = new JButton("Reject");
    JButton approveBtn = new JButton("Approve");
    JTable table;
    DefaultTableModel model;
    List<Receiver> receiver = new ArrayList<>();
    List<String[]> receiverRegData = new ArrayList<>();
    List<String[]> agentRegData = new ArrayList<>();
    String[] cols = new String[] {"IC / Passport", "Name", "Gender", "Home Address","Nationality", "Monthly Income", "Family Size", "Action"};
    

    public void receive (Admin admin) {
        this.admin = admin;
    }

    ReviewRegistration() {
        try {
        List<String> linesO = Files.readAllLines(Paths.get("ReceiverRegistration.txt"));
            for (int i = 0; i < linesO.size(); i++) {
                String[] items = linesO.get(i).split(";");
               
                    receiver.add (new Receiver(items[0], items[1], items[2], items[3], items[4], items[5], items[6], items[7]
                                                , items[8], Double.parseDouble(items[9]) ,Integer.valueOf(items[10])));
                    String[] colData = new String[] {items[1], items[3], items[4],items[7], items[8]
                                                    , items[9], items[10], null};
                    receiverRegData.add(colData);
                
                
            }
        }
        catch (IOException ex) {
            System.out.println (ex.toString());
        }
        model = new DefaultTableModel(receiverRegData.toArray(new Object[][] {}),cols);
        table = new JTable(model);
        table.getColumn("Action").setCellRenderer(new ButtonRenderer());
        table.getColumn("Action").setCellEditor(new ButtonEditor(table));
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(3).setPreferredWidth(200);
        columnModel.getColumn(2).setPreferredWidth(50);  
        columnModel.getColumn(0).setPreferredWidth(90);  
        columnModel.getColumn(1).setPreferredWidth(150);  
        columnModel.getColumn(4).setPreferredWidth(80);
        columnModel.getColumn(5).setPreferredWidth(70);
        columnModel.getColumn(6).setPreferredWidth(60);
        columnModel.getColumn(7).setPreferredWidth(200);
       
        setLayout(new BorderLayout());
        table.setRowHeight(40);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        JLabel title = new JLabel("Review User Registrations");
        title.setFont(new Font("Serif", Font.PLAIN, 30));
        add(title, BorderLayout.NORTH);
        add(table, BorderLayout.SOUTH);
        add(new JScrollPane(table));
    }
    
    class ButtonRenderer extends JPanel implements TableCellRenderer {
        public ButtonRenderer() {
            JButton rejectBtn = new JButton ("Reject");
            JButton approveBtn = new JButton("Approve");
            add(approveBtn);
            add(rejectBtn);
        }

        @Override
        public Component getTableCellRendererComponent (JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
            if (isSelected)
                this.setBackground(table.getSelectionBackground());
            else 
                this.setBackground(table.getBackground());
            return this;
        }
    }

    class ButtonEditor extends JPanel implements TableCellEditor {
        public ButtonEditor (JTable table) {
            add(approveBtn);
            add(rejectBtn);
            rejectBtn.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    cancelCellEditing();
                    int confirm = JOptionPane.showConfirmDialog(null, "Confirm to Reject?");
                    if (confirm == 0) {
                    int row = table.getSelectedRow();
                    try {
                        admin.deleteRegistration(receiver, table.getValueAt(row, 0).toString());
                    }
                    catch (IOException ex) {
                        System.out.println (ex.toString());
                    }   
                    model.removeRow(row);
                    table.changeSelection(row, 7, false, false);  
                }
                }
            });

            approveBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cancelCellEditing();
                    int confirm = JOptionPane.showConfirmDialog(null, "Confirm to Approve?");
                    if (confirm == 0) {
                    int row = table.getSelectedRow();
                    try {
                            admin.addNewReceiver(receiver, (String) table.getValueAt(row, 0));
                        
                    }
                    catch (IOException ex) {
                        System.out.println(ex.toString());
                    }
                    model.removeRow(row);
                    //Clear the previous selection and ensure the new cell is selected.
                    table.changeSelection(row, 7 , false, false);
                    
                    StringBuilder sbDel = new StringBuilder();
                    for (int k = 0; k < receiver.size(); k++) 
                        sbDel.append(receiver.get(k).toReceiverCSVString() + "\n");
                    
                    try {
                        Files.write(Paths.get("ReceiverRegistration.txt"), sbDel.toString().getBytes());
                    }
                    catch (IOException ex) {
                        System.out.println();
                    }
                }
                }
            });
        }
        
        //set the cell of selected button the same background as the jtable
        @Override 
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            if (isSelected) 
                this.setBackground(table.getSelectionBackground());
            else 
                this.setBackground(table.getBackground());
            return this;
        }
        @Override 
        public Object getCellEditorValue() {
            return "";
        }
        @Override 
        public boolean isCellEditable(java.util.EventObject e) {
            return true;
        }
        @Override
        public void addCellEditorListener(CellEditorListener l) {}
        @Override
        public void cancelCellEditing() {}
        @Override
        public void removeCellEditorListener (CellEditorListener l) {}
        @Override
        public boolean shouldSelectCell(EventObject evt) {return true;}
        @Override
        public boolean stopCellEditing() {return true;}  
    }
}

