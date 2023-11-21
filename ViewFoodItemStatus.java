import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ViewFoodItemStatus extends JPanel {
    private Map<String, ArrayList<Food>> foodStatus = new HashMap<>();
    private ArrayList<Food> foodList = new ArrayList<>();
    private JPanel gridPanel;

    ViewFoodItemStatus() throws IOException{
        loadFoodContentCSV();
        setFoodStatus();
        gridPanel = new JPanel (new GridLayout(0,2));
        for (var entry : foodStatus.entrySet()) {
            displayFood(entry.getKey(), entry.getValue());
        }

        JScrollPane vertical = new JScrollPane(gridPanel);
        vertical.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        vertical.setMinimumSize(new Dimension(300, 500));
        vertical.setPreferredSize(new Dimension(300, 400));
        JLabel title = new JLabel("FOOD ITEMS STATUS", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 30));
        title.setForeground(new Color(120, 90, 40));
        setLayout(new BorderLayout());
        add(title, BorderLayout.NORTH);

        add(vertical, BorderLayout.CENTER);
    }

    private void loadFoodContentCSV() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("FoodContent.csv"));
        for (int i = 0; i < lines.size(); i++) {
            String[] items = lines.get(i).split(",");
            foodList.add(new Food(items[0], items[1], Integer.valueOf(items[2]), items[3]));
        }
    }

    private void setFoodStatus() {
        ArrayList<Food> urgentlyNeeded = new ArrayList<>();
        ArrayList<Food> lowOnStock = new ArrayList<>();
        ArrayList<Food> wellStocked = new ArrayList<>();
        for (int j = 0; j < foodList.size(); j++) {
            if (foodList.get(j).getQuantity() < 10)
                urgentlyNeeded.add(foodList.get(j));
            else if (foodList.get(j).getQuantity() >= 10 && foodList.get(j).getQuantity() <= 20)
                lowOnStock.add(foodList.get(j));
            else 
                wellStocked.add(foodList.get(j));
        }
        foodStatus.put("Urgently Needed", urgentlyNeeded);
        foodStatus.put("Low on Stock", lowOnStock);
        foodStatus.put("Well Stocked", wellStocked);
    }

    private void displayFood(String status, ArrayList<Food> fooditems) {
        JPanel foodDetails = new JPanel();
        foodDetails.setLayout(null);
        JLabel foodStatus = new JLabel (status, SwingConstants.CENTER);
        foodStatus.setFont(new Font("Courier New", Font.BOLD, 25));
        foodStatus.setBounds(30,80,150,50);
        foodStatus.setBackground(Color.WHITE);
        gridPanel.add(foodStatus);

        JLabel details = new JLabel("<html><h2> ");
        details.setFont(new Font("Monospaced", Font.ITALIC, 16));
        
        for (Food fooditem : fooditems) {
            details.setText( details.getText() + "=> " + fooditem.getName() + "<br>");
            
        }
        details.setText(details.getText() + "</html>");
        details.setForeground(new Color(255,255,255));
        details.setOpaque(true);
        foodStatus.setOpaque(true);
 
        if (status.equals("Urgently Needed")) {
            foodStatus.setForeground(new Color(255, 0, 0));
            foodDetails.setBackground(new Color(255, 0, 0));
            details.setBackground(Color.RED);
        }
        else if (status.equals("Low on Stock")){
            foodStatus.setForeground(new Color(255, 171, 0));
            foodDetails.setBackground(new Color(255, 171, 0));
            details.setBackground(new Color(255, 171, 0));
        }
        else {
            foodStatus.setForeground(new Color(13, 209, 78));
            foodDetails.setBackground(new Color(13, 209, 78));
            details.setBackground(new Color(13, 209, 78));
        }
        details.setBounds(50,0, 400,170);
        foodDetails.add(details);
        foodStatus.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        foodDetails.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        gridPanel.add(foodDetails);
    }
}
