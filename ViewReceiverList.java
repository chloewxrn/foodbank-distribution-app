import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
  

public class ViewReceiverList extends JPanel {
 
    
     ArrayList<Receiver> receivers ;
     JPanel reportPanel;
     JPanel gridPanel;
     JPanel p1;
     Receiver receiver = new Receiver();


    ViewReceiverList() throws IOException{
        
        JLabel title = new JLabel("Receiver List");
        title.setFont(new Font("Serif", Font.PLAIN, 30));

        receivers = new ArrayList<>(loadReceivers());
        
        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(3, 1));
        p1 = new JPanel();
        p1.setLayout(new GridLayout(4, 1));
        p1.add(title);
        p1.add(p2);

        gridPanel = new JPanel(new GridLayout(0, 2));
        add(p1, BorderLayout.NORTH);
 
        gridPanel.repaint();
        JScrollPane vertical = new JScrollPane(gridPanel);
        vertical.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        vertical.setMinimumSize(new Dimension(1200, 800));
        vertical.setPreferredSize(new Dimension(1400, 500));

        add(vertical, BorderLayout.SOUTH);

        for (Receiver receiver : receivers) {
            DisplayReceivers(receiver);
        }
    }
    
    private void DisplayReceivers(Receiver receiver) {
        JLabel testingLabel = new JLabel("<html><br> " + "Receiver ID : " + receiver.ID
                + "<br> IC / Passport No. : " + receiver.IC + "<br> Name: " + receiver.name + "<br> Gender : "
                + receiver.gender  + "<br> Nationality : " + receiver.nationality
                + "<br> Email : " + receiver.email + "<br> Contact No. : " + receiver.contactNumber 
                + "<br> Home Address : " + receiver.homeAddress + "<br> Monthly Income: RM" + receiver.monthlyIncome
                + "<br> Family Size: " + receiver.famSize  + "</br> </html>");

        JPanel details = new JPanel();
        details.setLayout(null);
        details.setBounds(0, 0, 1000, 1000);
        details.add(testingLabel);

        gridPanel.add(testingLabel);
        gridPanel.add(details);
    }
    protected ArrayList<Receiver> loadReceivers() throws IOException{
        ArrayList<Receiver> receiver = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get("Receiver.txt"));
        for (int i = 0; i < lines.size(); i++){
            String[] items = lines.get(i).split(";");
            receiver.add (new Receiver(items[0], items[1], items[2], items[3], items[4], items[5], items[6], items[7]
                                        , items[8], Double.parseDouble(items[9]) ,Integer.valueOf(items[10])));
        }
        return receiver;
    }


    
}
