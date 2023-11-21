import javax.swing.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class ReportGenerate extends JPanel{
    String[] columnName = {"Description", "Number"};
    DefaultTableModel tableModel;
    JTable table = new JTable();
    JScrollPane scrollPane;

    public ReportGenerate() {

       
        JLabel title = new JLabel("Weekly Report");
        title.setFont(new Font("Serif", Font.BOLD, 40));
        title.setLocation(600,20);
        title.setSize(300,50);
        JLabel describe1 = new JLabel("<html><body> Prepared by : Cindy Wong <br><br> Admin ID: A0192823772733 <br><br> Date: 20/11/2021 </body></html>");
        describe1.setFont(new Font("Serif", Font.PLAIN, 20));
        describe1.setLocation(170,50);
        describe1.setSize(500,300);

        createTable();
        table.getTableHeader().setReorderingAllowed(false);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); 
        table.setFillsViewportHeight(true); 
        table.setRowHeight(table.getRowHeight() + 20);
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 20));
        table.setFont(new Font("Times", Font.BOLD, 17));

        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(100, 300, 1200, 250);
        scrollPane.setLocation(170,380);

        setLayout(null);
        add(title);
        add(describe1);
        add(scrollPane);
       
    }

    private void createTable() {
        tableModel = new DefaultTableModel(columnName, 1){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableModel.addRow(new String[]{"Financial Donation", "RM 2,320,120"});
        tableModel.addRow(new String[]{"Households Fed", "26,999"});
        tableModel.addRow(new String[]{"Utilities", "RM 131,120"});

        int numOfLocation = countNumOfLocation();
        tableModel.addRow(new String[]{"Number of Location", Integer.toString(numOfLocation)});

        tableModel.addRow(new String[]{"Food Category", "3"});
        
        int sumOfFoodAmount = countFoodAmount();
        tableModel.addRow(new String[]{"Food Amount", Integer.toString(sumOfFoodAmount)});

        tableModel.removeRow(0);
        table.setModel(tableModel);
    }

    private int countFoodAmount() {
        int sum = 0;
        try {
            BufferedReader bReader = new BufferedReader(new FileReader("FoodContent.csv"));
            String row;

            while ( (row = bReader.readLine()) != null) {
                String[] items = row.split(",");
                sum += Integer.valueOf(items[2]);
            }

            bReader.close();
        }catch (Exception e) { e.printStackTrace(); }

        return sum;
    }

    private int countNumOfLocation() {
        int count = 0;
        try {
            BufferedReader bReader = new BufferedReader(new FileReader("FoodDistributionInfo.txt"));
            String row;

            while ( (row = bReader.readLine()) != null) {
                count++;
            }

            bReader.close();
        }catch (Exception e) { e.printStackTrace(); }

        return count;
    }
}
