import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.*;
import java.awt.image.BufferedImage;

public class ReceiverProfile extends JPanel implements ActionListener {
    JButton editProfile;
    JLabel tenantName;
    JLabel IC, password, email, name, gender, contact, homeAddress, nationality, income, famsize, ID;
    private Switchable switcher;
    Receiver receiver;

    public void receive(Receiver receiver) {
        this.receiver = receiver;
        IC = new JLabel(receiver.IC);
        ID = new JLabel(receiver.ID);
        IC.setSize(190, 20);
        IC.setFont(new Font("Times", Font.BOLD, 16));
        IC.setLocation(850, 200);
        add(IC);
        ID.setSize(190, 20);
        ID.setLocation(850, 150);
        ID.setFont(new Font("Times", Font.BOLD, 16));
        add(ID);

        name = new JLabel(receiver.getName());
        name.setSize(190, 20);
        name.setLocation(850, 250);
        name.setFont(new Font("Times", Font.BOLD, 16));
        add(name);

        nationality = new JLabel(receiver.nationality);
        nationality.setSize(190, 20);
        nationality.setLocation(850, 300);
        nationality.setFont(new Font("Times", Font.BOLD, 16));
        add(nationality);

        gender = new JLabel(receiver.gender);
        gender.setSize(190, 20);
        gender.setLocation(850, 350);
        gender.setFont(new Font("Times", Font.BOLD, 16));
        add(gender);

        email = new JLabel(receiver.getEmail());
        email.setSize(190, 20);
        email.setLocation(850, 400);
        email.setFont(new Font("Times", Font.BOLD, 16));
        add(email);

        contact = new JLabel(receiver.getContact());
        contact.setSize(190, 20);
        contact.setLocation(850, 450);
        contact.setFont(new Font("Times", Font.BOLD, 16));
        add(contact);

        homeAddress = new JLabel(receiver.getAddress());
        homeAddress.setSize(400, 20);
        homeAddress.setLocation(850, 500);
        homeAddress.setFont(new Font("Times", Font.BOLD, 16));
        add(homeAddress);

        income = new JLabel(receiver.getIncome());
        income.setSize(190, 20);
        income.setLocation(850, 550);
        income.setFont(new Font("Times", Font.BOLD, 16));
        add(income);

        famsize = new JLabel(receiver.getFamSize());
        famsize.setSize(190, 20);
        famsize.setLocation(850, 600);
        famsize.setFont(new Font("Times", Font.BOLD, 16));
        add(famsize);

        password = new JLabel(receiver.getPassword());
        password.setSize(190, 20);
        password.setLocation(850, 650);
        password.setFont(new Font("Times", Font.BOLD, 16));
        add(password);

        
    }

    ReceiverProfile(Switchable switcher) {
        setLayout(null);
        this.switcher = switcher;

        JLabel tenantUserProfileTitles = new JLabel("Receiver : User Profile");
        tenantUserProfileTitles.setFont(new Font("Serif", Font.PLAIN, 30));
        tenantUserProfileTitles.setSize(500, 30);
        tenantUserProfileTitles.setLocation(100, 30);
        add(tenantUserProfileTitles);

        BufferedImage user;
        try {
            user = ImageIO.read(this.getClass().getResource("user.png"));
            JLabel userIcon = new JLabel(new ImageIcon(user));
            userIcon.setSize(100,65);
            userIcon.setLocation(770, 70);
            add(userIcon);
        } catch (IOException e) {
            
            e.printStackTrace();
        }
        

        JLabel receiverIDLabel = new JLabel("Receiver ID : ");
        receiverIDLabel.setSize(250, 40);
        receiverIDLabel.setFont((new Font("Times", Font.PLAIN, 16)));
        receiverIDLabel.setLocation(650, 150);
        add(receiverIDLabel);

        JLabel receiverICLabel = new JLabel("IC / Passport : ");
        receiverICLabel.setFont((new Font("Times", Font.PLAIN, 16)));
        receiverICLabel.setSize(250, 20);
        receiverICLabel.setLocation(650, 200);
        add(receiverICLabel);

        JLabel receiverNameLabel = new JLabel("Name :");
        receiverNameLabel.setFont((new Font("Times", Font.PLAIN, 16)));
        receiverNameLabel.setSize(250, 20);
        receiverNameLabel.setLocation(650, 250);
        add(receiverNameLabel);

        JLabel nationalityLabel = new JLabel("Nationality : ");
        nationalityLabel.setFont((new Font("Times", Font.PLAIN, 16)));
        nationalityLabel.setSize(250, 20);
        nationalityLabel.setLocation(650, 300);
        add(nationalityLabel);

        JLabel genderLabel = new JLabel("Gender : ");
        genderLabel.setFont((new Font("Times", Font.PLAIN, 16)));
        genderLabel.setSize(250, 20);
        genderLabel.setLocation(650, 350);
        add(genderLabel);

        JLabel EmailLabel = new JLabel("Email :");
        EmailLabel.setFont((new Font("Times", Font.PLAIN, 16)));
        EmailLabel.setSize(250, 20);
        EmailLabel.setLocation(650, 400);
        add(EmailLabel);

        JLabel contactLabel = new JLabel("Contact No : ");
        contactLabel.setFont((new Font("Times", Font.PLAIN, 16)));
        contactLabel.setSize(250, 20);
        contactLabel.setLocation(650, 450);
        add(contactLabel);

        JLabel homeAddressLabel = new JLabel("Home Address : ");
        homeAddressLabel.setFont((new Font("Times", Font.PLAIN, 16)));
        homeAddressLabel.setSize(450, 20);
        homeAddressLabel.setLocation(650, 500);
        add(homeAddressLabel);

        JLabel incomeLabel = new JLabel("Monthly Income : ");
        incomeLabel.setFont((new Font("Times", Font.PLAIN, 16)));
        incomeLabel.setSize(250, 20);
        incomeLabel.setLocation(650, 550);
        add(incomeLabel);

        JLabel famsizeLabel = new JLabel("Family Size: ");
        famsizeLabel.setFont((new Font("Times", Font.PLAIN, 16)));
        famsizeLabel.setSize(250, 20);
        famsizeLabel.setLocation(650, 600);
        add(famsizeLabel);

        JLabel PasswordLabel = new JLabel("Password :");
        PasswordLabel.setFont((new Font("Times", Font.PLAIN, 16)));
        PasswordLabel.setSize(250, 20);
        PasswordLabel.setLocation(650, 650);
        add(PasswordLabel);

        editProfile = new JButton("Edit Profile");
        editProfile.setSize(100, 20);
        editProfile.setLocation(770, 700);
        editProfile.addActionListener(this);
        // add(editProfile, BorderLayout.CENTER);
        add(editProfile);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == editProfile) {
            new ReceiverUpdateProfile();
            switcher.show("updateProf");

        }
    }

}

class ReceiverUpdateProfile extends JPanel implements ActionListener {
    JButton updateProfile;
    JLabel tenantUsername, ID,IC, Name, gender, nationality;
    JTextField chg_Password, chg_Email, chg_contact, chg_homeAddress, chg_income, chg_famsize;
    Receiver receiver;
    //private Switchable switcher;

    public void receive(Receiver receiver) {
        this.receiver = receiver;

        ID = new JLabel(receiver.ID);
        ID.setSize(190, 20);
        ID.setLocation(850, 150);
        add(ID);

        IC = new JLabel(receiver.IC);
        IC.setSize(190, 20);
        IC.setLocation(850, 200);
        add(IC);

        Name = new JLabel(receiver.getName());
        Name.setSize(190, 20);
        Name.setLocation(850, 250);
        add(Name);
        
        nationality = new JLabel(receiver.nationality);
        nationality.setSize(190, 20);
        nationality.setLocation(850, 300);
        add(nationality);

        gender = new JLabel(receiver.gender);
        gender.setSize(190, 20);
        gender.setLocation(850, 350);
        add(gender);

        chg_Email = new JTextField(receiver.getEmail());
        chg_Email.setSize(290, 20);
        chg_Email.setLocation(850, 400);
        add(chg_Email);


        chg_contact = new JTextField(receiver.getContact());
        chg_contact.setSize(290, 20);
        chg_contact.setLocation(850, 450);
        add(chg_contact);

        chg_homeAddress = new JTextField(receiver.getAddress());
        chg_homeAddress.setSize(290, 20);
        chg_homeAddress.setLocation(850, 500);
        add(chg_homeAddress);

        chg_income = new JTextField(receiver.getIncome());
        chg_income.setSize(290, 20);
        chg_income.setLocation(850, 550);
        add(chg_income);

        chg_famsize = new JTextField(receiver.getFamSize());
        chg_famsize.setSize(290, 20);
        chg_famsize.setLocation(850, 600);
        add(chg_famsize);

        chg_Password = new JTextField(receiver.getPassword());
        chg_Password.setSize(290, 20);
        chg_Password.setLocation(850, 650);
        add(chg_Password);



    }

    ReceiverUpdateProfile() {
        setLayout(null);
        JLabel tenantUserProfileTitles = new JLabel("Receiver : User Profile");
        tenantUserProfileTitles.setFont(new Font("Serif", Font.PLAIN, 30));
        tenantUserProfileTitles.setSize(500, 30);
        tenantUserProfileTitles.setLocation(100, 30);
        add(tenantUserProfileTitles);

        BufferedImage wPic;
        try {
            wPic = ImageIO.read(this.getClass().getResource("user.png"));
            JLabel wIcon = new JLabel(new ImageIcon(wPic));
            wIcon.setSize(100,65);
            wIcon.setLocation(770, 70);
            add(wIcon);
        } catch (IOException e) {
            
            e.printStackTrace();
        }
        

        JLabel receiverIDLabel = new JLabel("Receiver ID : ");
        receiverIDLabel.setSize(250, 40);
        receiverIDLabel.setFont((new Font("Times", Font.PLAIN, 16)));
        receiverIDLabel.setLocation(650, 150);
        add(receiverIDLabel);

        JLabel receiverICLabel = new JLabel("IC / Passport : ");
        receiverICLabel.setFont((new Font("Times", Font.PLAIN, 16)));
        receiverICLabel.setSize(250, 20);
        receiverICLabel.setLocation(650, 200);
        add(receiverICLabel);

        JLabel receiverNameLabel = new JLabel("Name :");
        receiverNameLabel.setFont((new Font("Times", Font.PLAIN, 16)));
        receiverNameLabel.setSize(250, 20);
        receiverNameLabel.setLocation(650, 250);
        add(receiverNameLabel);

        JLabel nationalityLabel = new JLabel("Nationality : ");
        nationalityLabel.setFont((new Font("Times", Font.PLAIN, 16)));
        nationalityLabel.setSize(250, 20);
        nationalityLabel.setLocation(650, 300);
        add(nationalityLabel);

        JLabel genderLabel = new JLabel("Gender : ");
        genderLabel.setFont((new Font("Times", Font.PLAIN, 16)));
        genderLabel.setSize(250, 20);
        genderLabel.setLocation(650, 350);
        add(genderLabel);

        JLabel EmailLabel = new JLabel("Email :");
        EmailLabel.setFont((new Font("Times", Font.PLAIN, 16)));
        EmailLabel.setSize(250, 20);
        EmailLabel.setLocation(650, 400);
        add(EmailLabel);

        JLabel contactLabel = new JLabel("Contact No : ");
        contactLabel.setFont((new Font("Times", Font.PLAIN, 16)));
        contactLabel.setSize(250, 20);
        contactLabel.setLocation(650, 450);
        add(contactLabel);

        JLabel homeAddressLabel = new JLabel("Home Address : ");
        homeAddressLabel.setFont((new Font("Times", Font.PLAIN, 16)));
        homeAddressLabel.setSize(450, 20);
        homeAddressLabel.setLocation(650, 500);
        add(homeAddressLabel);

        JLabel incomeLabel = new JLabel("Monthly Income : ");
        incomeLabel.setFont((new Font("Times", Font.PLAIN, 16)));
        incomeLabel.setSize(250, 20);
        incomeLabel.setLocation(650, 550);
        add(incomeLabel);

        JLabel famsizeLabel = new JLabel("Family Size: ");
        famsizeLabel.setFont((new Font("Times", Font.PLAIN, 16)));
        famsizeLabel.setSize(250, 20);
        famsizeLabel.setLocation(650, 600);
        add(famsizeLabel);

        JLabel PasswordLabel = new JLabel("Password :");
        PasswordLabel.setFont((new Font("Times", Font.PLAIN, 16)));
        PasswordLabel.setSize(250, 20);
        PasswordLabel.setLocation(650, 650);
        add(PasswordLabel);


        updateProfile = new JButton("Save");
        updateProfile.setSize(100, 20);
        updateProfile.setLocation(770, 700);
        updateProfile.addActionListener(this);
        add(updateProfile);

    }

 
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateProfile) {
            if  (chg_Email.getText().equals("") || chg_Password.getText().equals("") || chg_contact.getText().equals("") || chg_famsize.getText().equals("") || chg_homeAddress.getText().equals("") || chg_income.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Error : Field cannot be left blank!");
            }
            try {
                if (updateTenantProfile(receiver.getIC(), chg_homeAddress.getText()) == 0) {
            
                    ArrayList<Receiver> tenantUpdation = readReceiverFromFile();
                    for (int y = 0; y < tenantUpdation.size(); y++) {
                        if (tenantUpdation.get(y).getIC().equals(receiver.getIC())) {
                            tenantUpdation.get(y).editProfile( chg_Email.getText(), chg_contact.getText(),chg_homeAddress.getText(),
                                                               Double.parseDouble(chg_income.getText()), Integer.parseInt(chg_famsize.getText()), 
                                                               chg_Password.getText());
                        }
                    }

                   
                    saveTenantRegisterToFile(tenantUpdation);
                    JOptionPane.showMessageDialog(this, "Profile Update Successfully!");
                
                }

               
                else if (updateTenantProfile(receiver.getIC(), chg_homeAddress.getText()) == 1) {
                    JOptionPane.showMessageDialog(this, "This address is registered. Please try again using other address");

                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }
    }

    private int updateTenantProfile(String IC, String homeAddress) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("Receiver.txt"));

        for (int i = 0; i < lines.size(); i++) {
            String[] items = lines.get(i).split(";");
            String addressCSV = items[7];
            String icCSV = items[1];

            if (homeAddress.equals(addressCSV) && !IC.equals(icCSV))
                return 1;

        }

        return 0;
    }

    private void saveTenantRegisterToFile(ArrayList<Receiver> potentialTenant) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < potentialTenant.size(); i++) {
            sb.append(potentialTenant.get(i).toReceiverCSVString() + "\n");
        }

        Files.write(Paths.get("Receiver.txt"), sb.toString().getBytes());
    }

    private ArrayList<Receiver> readReceiverFromFile() throws IOException {
        ArrayList<Receiver> tenant = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get("Receiver.txt"));
        for (int i = 0; i < lines.size(); i++) {
            String[] items = lines.get(i).split(";");
            tenant.add(new Receiver(items[0], items[1], items[2], items[3], items[4], items[5], items[6], items[7]
                                     , items[8], Double.parseDouble(items[9]) ,Integer.valueOf(items[10])));
        }
        return tenant;
    }

    public static List<String[]> get(String filename) throws IOException {
        List<String[]> data = new ArrayList<String[]>();
        String testRow;
        BufferedReader br = new BufferedReader(new FileReader(filename));
        while ((testRow = br.readLine()) != null) {
            String[] line = testRow.split(";");
            data.add(line);
        }
        br.close();
        return data;
    }

    public void saveUser(List<String[]> getUserArrayList) throws IOException {
        FileWriter fw = new FileWriter("User.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        for (String y[] : getUserArrayList) {
            bw.write((Arrays.toString(y).replace("[", "").replace("]", "").replace(" ", "").replace(",", ";")));
            bw.newLine();
        }
        bw.close();
        fw.close();

    }

}





class viewDistributionInfo extends JPanel implements ActionListener{
    JButton backHomePage;
    JLabel tenantName;
    JLabel applicationID, receiverID, category, date, time, location;
    private Switchable switcher;
    Receiver receiver;
    ArrayList<String> foodDistributionInfo;


    public void receive(Receiver receiver) throws IOException {
        this.receiver = receiver;
        
        foodDistributionInfo = new ArrayList<>();
        try {
            foodDistributionInfo = receiver.distributionInfoAlertNotification(receiver);
        } catch (IOException e) {
            e.printStackTrace();
        }
    

        
        // Application ID, Receiver ID, Category, Date, Time, Location
        applicationID = new JLabel(foodDistributionInfo.get(0));
        applicationID.setSize(190, 20);
        applicationID.setLocation(850, 200);
        applicationID.setFont(new Font("Times", Font.BOLD, 16));
        add(applicationID);

        receiverID = new JLabel(receiver.ID);
        receiverID.setSize(190, 20);
        receiverID.setLocation(850, 150);
        receiverID.setFont(new Font("Times", Font.BOLD, 16));
        add(receiverID);


        category = new JLabel(foodDistributionInfo.get(2));
        category.setSize(190, 20);
        category.setLocation(850, 250);
        category.setFont(new Font("Times", Font.BOLD, 16));
        add(category);

        date = new JLabel(foodDistributionInfo.get(3));
        date.setSize(190, 20);
        date.setLocation(850, 300);
        date.setFont(new Font("Times", Font.BOLD, 16));
        add(date);

        time = new JLabel(foodDistributionInfo.get(4));
        time.setSize(190, 20);
        time.setLocation(850, 350);
        time.setFont(new Font("Times", Font.BOLD, 16));
        add(time);

        location = new JLabel(foodDistributionInfo.get(5));
        location.setSize(300, 20);
        location.setLocation(850, 400);
        location.setFont(new Font("Times", Font.BOLD, 16));
        add(location);



}


    viewDistributionInfo(Switchable switcher) {
        setLayout(null);
        this.switcher = switcher;
        JLabel tenantUserDistributionInfoTitle = new JLabel("Food Distribution Info");
        tenantUserDistributionInfoTitle.setFont(new Font("Serif", Font.PLAIN, 30));
        tenantUserDistributionInfoTitle.setSize(500, 30);
        tenantUserDistributionInfoTitle.setLocation(650, 30);
        add(tenantUserDistributionInfoTitle);


        // Application ID, Receiver ID, Category, Date, Time, Location
        JLabel receiverIDLabel = new JLabel("Receiver ID : ");
        receiverIDLabel.setSize(250, 40);
        receiverIDLabel.setFont((new Font("Times", Font.PLAIN, 16)));
        receiverIDLabel.setLocation(650, 150);
        add(receiverIDLabel);

        JLabel applicationIDLabel = new JLabel("Application ID : ");
        applicationIDLabel.setFont((new Font("Times", Font.PLAIN, 16)));
        applicationIDLabel.setSize(250, 20);
        applicationIDLabel.setLocation(650, 200);
        add(applicationIDLabel);

        JLabel categoryLabel = new JLabel("Category :");
        categoryLabel.setFont((new Font("Times", Font.PLAIN, 16)));
        categoryLabel.setSize(250, 20);
        categoryLabel.setLocation(650, 250);
        add(categoryLabel);

        JLabel dateLabel = new JLabel("Date : ");
        dateLabel.setFont((new Font("Times", Font.PLAIN, 16)));
        dateLabel.setSize(250, 20);
        dateLabel.setLocation(650, 300);
        add(dateLabel);

        JLabel timeLabel = new JLabel("Time : ");
        timeLabel.setFont((new Font("Times", Font.PLAIN, 16)));
        timeLabel.setSize(250, 20);
        timeLabel.setLocation(650, 350);
        add(timeLabel);

        JLabel locationLabel = new JLabel("Location :");
        locationLabel.setFont((new Font("Times", Font.PLAIN, 16)));
        locationLabel.setSize(250, 20);
        locationLabel.setLocation(650, 400);
        add(locationLabel);

        backHomePage = new JButton("Back");
        backHomePage.setSize(100, 20);
        backHomePage.setLocation(770, 600);
        backHomePage.addActionListener(this);
        add(backHomePage);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backHomePage){
            switcher.show("home");
        }
    }

}