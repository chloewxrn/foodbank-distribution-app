
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Donation {
    private String ID;
    private String name;
    private String email;
    private String address;
    private double amount;
    private boolean anonymous;
    public Donation() {}
    Donation(String ID, String name, String email, String address, double amount, Boolean anonymous) {
        this.ID = ID;
        this.name = name;
        this.address = address;
        this.email = email;
        this.amount = amount;
        this.anonymous = anonymous;
    }

    public String DonationCSVString() {
        return ID + ';' + name + ';' + email + ';' + address + ';' + amount + ';' + anonymous; 
    }

    public void saveDonation(String ID, String name, String email, String address, double amount, Boolean anonymous){
        StringBuilder sb = new StringBuilder();
        Donation donor = new Donation(ID, name, email, address, amount, anonymous);
        sb.append(donor.DonationCSVString());
        String file = "Donation.txt";
        FileWriter fw;
        try {
            fw = new FileWriter(file,true);
             BufferedWriter bw = new BufferedWriter(fw);
             bw.write(sb.toString() + "\n");
             bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
       
        
    }
}
