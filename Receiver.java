import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Receiver extends User{

protected String gender;
protected String contactNumber;
protected String nationality;
protected String homeAddress;
protected int famSize;
protected double monthlyIncome;


    public Receiver(){}
    
    public Receiver(String ID, String IC, String password, String name, String gender, String email, String contactNumber, String homeAddress, String nationality, double monthlyIncome, int famSize ){
       super(ID, IC, password, name, email);
        this.gender = gender;
        this.contactNumber = contactNumber;
        this.homeAddress = homeAddress;
        this.nationality = nationality;
        this.monthlyIncome = monthlyIncome;
        this.famSize = famSize;
    }

  
    public String toReceiverCSVString(){
        return ID +  ';' + IC + ";" + password + ';' + name + ";" + gender + ";" + email + ';' + contactNumber +  ";"  + homeAddress + ';' + nationality + ';' + monthlyIncome + ';' + famSize;
    }

   
    public void saveReceiverReg (String ID, String IC, String password, String name, String gender, String email, String contactNum, String homeAddress, String nationality, double monthlyIncome, int famSize) throws IOException{
        StringBuilder sb = new StringBuilder();
        Receiver receiver = new Receiver ( ID, IC, password, name, gender, email, contactNum, homeAddress, nationality, monthlyIncome, famSize);
        sb.append(receiver.toReceiverCSVString());
        String file = "ReceiverRegistration.txt";
        FileWriter fw = new FileWriter(file,true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(sb.toString() + "\n");
        bw.close();
       
    }

    public int checkDuplicates(String ic, String homeAddress) throws IOException {
      
        List<String> lines = Files.readAllLines(Paths.get("Receiver.txt"));

        for (int i = 0; i < lines.size(); i++) {
            String[] items = lines.get(i).split(";");
            String icCSV = items[1];
            String homeAddressCSV = items[8];

            if(ic.equals(icCSV) || homeAddress.equals(homeAddressCSV) )
                return 1;

        }

        return 0;
    }

    protected String getName(){
        return name;
    }

    protected String getIC(){
        return IC;
    }

    protected String getPassword(){
        return password;
    }

    protected String getEmail(){
        return email;
    }

    protected String getContact(){
        return contactNumber;
    }

    protected String getAddress(){
        return homeAddress;
    }

    protected String getIncome(){
        return Double.toString(monthlyIncome);
    }

    protected String getFamSize(){
        return Integer.toString(famSize);
    }

    protected void editProfile(String chg_email,  String chg_contact, String chg_address, double chg_income, int chg_famsize, String chg_password) {
        email = chg_email;
        contactNumber = chg_contact;
        homeAddress = chg_address;
        monthlyIncome = chg_income;
        famSize = chg_famsize;
        password = chg_password;
    }


    public ArrayList<String> distributionInfoAlertNotification(Receiver receiver) throws IOException{

        ArrayList<String> tempFoodDistributionInfo = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get("FoodApplication.csv"));

        for (int i = 0; i < lines.size(); i++) {
            String[] items = lines.get(i).split(",");
            String applicationID = items[0];
            String receiverID = items[1];
            String tempCategoryApplied = items[2];
            String tempReceiverID = receiver.ID;

            if(tempReceiverID.equals(receiverID)){
                List<String> lines2 = Files.readAllLines(Paths.get("FoodDistributionInfo.txt"));

                for (int y = 0; y < lines2.size(); y++) {
                    String[] items2 = lines2.get(y).split(";");
                    String tempCategoryInTxt = items2[1];
                    if(tempCategoryApplied.equals(tempCategoryInTxt)){
                        // Application ID, Receiver ID, Category, Date, Time, Location
                        tempFoodDistributionInfo.add(applicationID);
                        tempFoodDistributionInfo.add(receiverID);
                        tempFoodDistributionInfo.add(tempCategoryInTxt);
                        tempFoodDistributionInfo.add(items2[2]);
                        tempFoodDistributionInfo.add(items2[3]);
                        tempFoodDistributionInfo.add(items2[4]);
                    }


                }
            }
        }
        return tempFoodDistributionInfo;
    }


    public int checkApplicationExist(Receiver receiver) throws IOException{
        ArrayList<String> tempFoodDistributionInfo = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get("FoodApplication.csv"));

        for (int i = 0; i < lines.size(); i++) {
            String[] items = lines.get(i).split(",");
            String applicationID = items[0];
            String receiverID = items[1];
            String tempReceiverID = receiver.ID;
            if (tempReceiverID.equals(receiverID)){
                return 1;
            }

        }


        return 0;
    }
}
