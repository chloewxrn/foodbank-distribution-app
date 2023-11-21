import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JTextField;

import java.nio.file.Files;
import java.nio.file.Paths;


public class FoodDistribution {
    protected ArrayList<FoodDistribution> distributionInfo = new ArrayList<FoodDistribution>();
    //String receiverID;
    String distributionID;
    // String FoodDistributionID;
    String category;
    String distributionDate;
    String distributionTime;
    String location;
   // int foodQuantity;
   // int receiverAmount;

    public FoodDistribution( ){}
    /*
    public FoodDistribution(String receiverID, String distributionID, String category,String location, String distributoinDate, String distributionTime, int foodQuantity, int receiverAmount){
        this.receiverID = receiverID;
        this.distributionID = distributionID;
        
        this.category = category;

        this.location = location;
        this.distributionDate = distributionDate;
        this.distributionTime = distributionTime;
       // this.foodQuantity = foodQuantity;
       // this.receiverAmount = receiverAmount;
    }
    */


    public FoodDistribution(String FoodDistributionID, String category,String distributionDate, String distributionTime, String location){
        this.distributionID = FoodDistributionID;
        this.category = category;
        this.distributionDate = distributionDate;
        this.distributionTime = distributionTime;
        this.location = location;

    }


    public String toFoodDistributionCSV(){
        return distributionID + ";" + category + ";" + distributionDate +";" + distributionTime+  ";" + location ;
    }

   
    public void createFoodDistributionInfo(String FoodDistributionID,String category,String distributionDate, String distributionTime,String location ) throws IOException{
        StringBuilder sb = new StringBuilder();
        FoodDistribution info = new FoodDistribution(FoodDistributionID,category, distributionDate, distributionTime, location);
        sb.append(info.toFoodDistributionCSV());
        
        String file = "FoodDistributionInfo.txt";
        FileWriter fw = new FileWriter(file,true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(sb.toString() + "\n");
        bw.close();

    }

    public void deleteFoodDistributionInfo(List<FoodDistribution> foodDistribution, String DistributionID) throws IOException {
        
        for(int i=0;i<foodDistribution.size();i++){
            if(DistributionID.equals(foodDistribution.get(i).getID())){
                foodDistribution.remove(i);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < foodDistribution.size(); k++) 
            sb.append(foodDistribution.get(k).toFoodDistributionCSV() + "\n");
       
        Files.write(Paths.get("FoodDistributionInfo.txt"), sb.toString().getBytes()); 
    }





    public String getID(){
        return distributionID;
    }



    protected void saveDistributionInfo (String FoodDistributionID,String category,String location, String distributionDate,String distributionTime ) throws IOException{
        StringBuilder sb = new StringBuilder();
        FoodDistribution info = new FoodDistribution( FoodDistributionID, category, location,  distributionDate, distributionTime );
        sb.append(info.toFoodDistributionCSV());
        String file = "FoodDistributionInfo.txt ";
        FileWriter fw = new FileWriter(file,true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(sb.toString() + "\n");
        bw.close();
    
    }

    public void saveAfterEdit(String propertyID, JTextField[] txt ) throws IOException {

        List<String> lines = Files.readAllLines(Paths.get("FoodDistributionInfo.txt"));
        ArrayList<FoodDistribution> info = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            String[] items = lines.get(i).split(";");
            String pptID = items[0];

            if (propertyID.equals(pptID)){
                items[0] = txt[0].getText();
                items[1] = txt[1].getText();
                items[2] = txt[2].getText();
                items[3] = txt[3].getText();
                items[4] = txt[4].getText();

            }
            info.add(new FoodDistribution(items[0], items[1], items[2], items[3], items[4]));   
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < info.size(); i++){
           sb.append (info.get(i).toFoodDistributionCSV() + "\n");
       }
        Files.write(Paths.get("FoodDistributionInfo.txt") , sb.toString().getBytes());
    }



   
    


}
