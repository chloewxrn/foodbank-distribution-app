import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;

public class Food {
    private String foodID;
    private String name;
    private int quantity;
    private String description;

    public Food () {}
    public Food (String foodID, String name, int quantity, String description) {
        this.foodID = foodID;
        this.name = name;
        this.quantity = quantity;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String toFoodCSV(){
        return foodID + "," + name + "," + quantity + "," + description;
    }

    public String getID(){
        return foodID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void deleteFoodContent(List<Food> food, String FoodID) throws IOException {
        
        for(int i=0;i<food.size();i++){
            if(FoodID.equals(food.get(i).foodID)){
                food.remove(i);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < food.size(); k++) 
            sb.append(food.get(k).toFoodCSV() + "\n");
       
        Files.write(Paths.get("FoodContent.csv"), sb.toString().getBytes()); 
    }

    public void saveAfterEdit(String FoodID, JTextField[] txt ) throws IOException {

        List<String> lines = Files.readAllLines(Paths.get("FoodContent.csv"));
        ArrayList<Food> info = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            String[] items = lines.get(i).split(",");
            String foodID = items[0];

            if (FoodID.equals(foodID)){
                items[0] = txt[0].getText();
                items[1] = txt[1].getText();
                items[2] = txt[2].getText();
                items[3] = txt[3].getText();
            }
            info.add(new Food(items[0], items[1], Integer.parseInt(items[2]), items[3]));   
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < info.size(); i++){
           sb.append (info.get(i).toFoodCSV() + "\n");
       }
        Files.write(Paths.get("FoodContent.csv") , sb.toString().getBytes());
    }

    public void createFoodContent(String FoodID, String name, int quantity, String description) throws IOException{
        StringBuilder sb = new StringBuilder();
        Food info = new Food(FoodID, name, quantity, description);
        sb.append(info.toFoodCSV());
        
        String file = "FoodContent.csv";
        FileWriter fw = new FileWriter(file,true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(sb.toString() + "\n");
        bw.close();

    }
}