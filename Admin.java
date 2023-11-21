import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.FileWriter;
import java.util.List;
import java.util.Date;

public class Admin extends User {

 
    public Admin() {}
    
    public Admin(String ID, String IC,  String password,  String name,String email) {
        super(ID, IC, password, name, email);
    }

    public void addNewAdmin(String id, String ic, String password, String name, String email) throws IOException {
        StringBuilder sb = new StringBuilder();
        String file = "User.csv";
        Admin newAdmin = new Admin (id, ic, name, password, email);
        sb.append(newAdmin.AdminCSVString());
        FileWriter fw = new FileWriter(file,true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(sb.toString() + "\n");
        bw.close();
        file = "AdminProperty.csv";
        fw = new FileWriter(file,true);
        bw = new BufferedWriter(fw);
        StringBuilder sb1 = new StringBuilder();
        sb1.append(id);
        bw.write(sb1.toString() + "\n");
        bw.close();
    }

    public int checkDuplicates(String username)  throws IOException {
        List<String> lines = Files.readAllLines(Paths.get( "User.txt"));

        for (int i = 0; i < lines.size(); i++) {
            String[] items = lines.get(i).split(",");
            String userNameCSV = items[1];
            if(username.equals(userNameCSV))
                return 1;
        }
        return 0;
    }


    public void addNewReceiver(List<Receiver> receiver, String IC) throws IOException{
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < receiver.size(); i++) {
            if ((receiver.get(i).IC).equals(IC)) {
                (receiver.get(i)).ID += (new Date()).getTime();
                sb.append((receiver.get(i)).toReceiverCSVString());
                receiver.remove(i);
                break;
            }
        }
        String file = "Receiver.txt";
        FileWriter fw = new FileWriter(file,true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(sb.toString() + "\n");
        bw.close();
        String file2 = "User.txt";
        FileWriter fw2 = new FileWriter(file2,true);
        BufferedWriter bw2 = new BufferedWriter(fw2);
        bw2.write(sb.toString() + "\n");
        bw2.close();
    }

    public void deleteRegistration(List<Receiver> receiver, String ic) throws IOException {
        
            for (int i = 0; i < receiver.size(); i++)
                if (ic.equals(receiver.get(i).IC))
                  receiver.remove(i);
        
       
        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < receiver.size(); k++) 
            sb.append(receiver.get(k).toReceiverCSVString() + "\n");
        
        Files.write(Paths.get("ReceiverRegistration.txt"), sb.toString().getBytes()); 
    }

    public String AdminCSVString() {
        return ID + ',' + IC + ',' + password + ',' + name + "," + email; 
    }
}
