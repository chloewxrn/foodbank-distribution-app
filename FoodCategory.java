import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDateTime; 
import java.time.format.DateTimeFormatter;

public class FoodCategory extends JPanel {
    private Map<Character, ArrayList<Food>> foodCategories = new HashMap<>();
    private ArrayList<Food> foodList = new ArrayList<>();
    private JPanel gridPanel1;
    private Receiver receiver;
    private boolean flag = false;

    public void receive(Receiver receiver) {
        this.receiver = receiver;
    }
    FoodCategory() throws IOException {
        loadFoodContentCSV();
        setFoodCategory();
        gridPanel1 = new JPanel (new GridLayout(0,2));
        for (var entry : foodCategories.entrySet()) {
            displayFood(entry.getKey(), entry.getValue());
        }
        gridPanel1.revalidate();
        gridPanel1.repaint();
        JScrollPane vertical = new JScrollPane(gridPanel1);
        vertical.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        vertical.setMinimumSize(new Dimension(300, 500));
        vertical.setPreferredSize(new Dimension(300, 400));
        JLabel title = new JLabel("FOOD CATEGORIES", SwingConstants.CENTER);
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

    private void setFoodCategory() {
        int count = 0;
        Character category = 'A';
        for (int j = 0; j < (foodList.size() / 5); j++) {
            ArrayList<Food> foodCategory = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                foodCategory.add(foodList.get(count));
                count++;
            }
            foodCategories.put(category++, foodCategory);
        }
        if (count < foodList.size()) {
            ArrayList<Food> foodCategory = new ArrayList<>();
            for (int i = count; i < foodList.size(); i++) {
                foodCategory.add(foodList.get(count++));
            }
            foodCategories.put(category++, foodCategory);
        }

    }

    private void displayFood(Character category, ArrayList<Food> fooditems) {
        JPanel foodDetails = new JPanel();
        foodDetails.setLayout(null);
        JLabel foodCat = new JLabel ("FOOD CATEGORY " + category, SwingConstants.CENTER);
        foodCat.setFont(new Font("Courier New", Font.BOLD, 25));
        foodCat.setForeground(new Color(120, 90, 40));
        foodCat.setBounds(30,80,150,50);
        gridPanel1.add(foodCat);

        JLabel details = new JLabel("<html><h2> ");
        
        for (Food fooditem : fooditems) {
            details.setText( details.getText() + "=> " + fooditem.getName() + "<br>");
            
        }
        details.setText(details.getText() + "</html>");
        JButton applyBtn = new JButton("APPLY");
        applyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (flag == false) {
                    JOptionPane.showMessageDialog(null, "Application Successful!");
                    StringBuilder sb = new StringBuilder();
                    String file = "FoodApplication.csv";
                    sb.append(foodApplicationToCSVString(category,receiver.ID, "Successful"));
                    try {
                        FileWriter fw = new FileWriter(file,true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        bw.write(sb.toString() + "\n");
                        bw.close();
                    }
                    catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    flag = true;
                }
                else {
                    JOptionPane.showMessageDialog(null, "Application Failed! Please apply again after 7 days");
                }
            }
        });
        details.setBounds(50,0, 300,200);
        applyBtn.setBounds(300,70,100,30);
        foodDetails.add(details);
        foodDetails.add(applyBtn);
        foodCat.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        foodDetails.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        //foodDetails.setBorder(BorderFactory.createMatteBorder(1,0,1,1, Color.BLACK));
        gridPanel1.add(foodDetails);



    }

    public String foodApplicationToCSVString(Character category,String id, String status) {
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        
        return "A" + (new Date()).getTime() + "," + id + ",FC_" + category + ',' + LocalDateTime.now().format(myFormatObj) + ',' + status;
    }
}