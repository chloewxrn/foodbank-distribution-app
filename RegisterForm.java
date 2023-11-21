import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;


public class RegisterForm extends JPanel implements ActionListener{

    JButton Submit;
    JRadioButton Male, Female;
    JTextField NameTextField,ICText, ContacTextField, EmailText,  PasswordText, AgeText, AddressText,  NationalityText, IncomeText, FamSizeText;
    private Switchable switcher;

    RegisterForm(Switchable switcher){
        this.switcher = switcher;
        setLayout(null);
        
        JLabel RegTitle = new JLabel ("Receiver Registration Form");
        RegTitle.setFont(new Font("Serif", Font.PLAIN, 30));
        RegTitle.setSize(800, 40);
        RegTitle.setLocation(620, 30);
        add(RegTitle);

        JLabel Name = new JLabel("Name");
        Name.setSize(100, 20);
        Name.setLocation(650, 100);
        add(Name);

        NameTextField = new JTextField();
        NameTextField.setSize(190, 20);
        NameTextField.setLocation(750, 100);
        add(NameTextField);

        JLabel IC = new JLabel("IC / Passport");
        IC.setSize(100,20);
        IC.setLocation(650,150);
        add(IC);
        
        ICText = new JTextField();
        ICText.setSize(190,20);
        ICText.setLocation(750,150);
        add(ICText);

        JLabel ownerContact = new JLabel("Contact Number");
        ownerContact.setSize(100,20);
        ownerContact.setLocation(650, 200);
        add(ownerContact);

        ContacTextField = new JTextField();
        ContacTextField.setSize(190,20);
        ContacTextField.setLocation(750,200);
        add(ContacTextField);

        JLabel ownerEmail = new JLabel ("Email");
        ownerEmail.setSize(100,20);
        ownerEmail.setLocation(650,250);
        add(ownerEmail);

        EmailText = new JTextField();
        EmailText.setSize(190,20);
        EmailText.setLocation(750,250);
        add(EmailText);

        JLabel ownerGender = new JLabel("Gender");
        ownerGender.setSize(100,20);
        ownerGender.setLocation(650,300);
        add(ownerGender);

        Male = new JRadioButton("Male");
        Male.setSize(75, 20);
        Male.setLocation(750,300);
        add(Male);

        Female = new JRadioButton("Female");
        Female.setSize(75, 20);
        Female.setLocation(850,300);
        add(Female);
        

        JLabel Address = new JLabel("Home Address");
        Address.setSize(100,20);
        Address.setLocation(650,350);
        add(Address);

        AddressText = new JTextField();
        AddressText.setSize(190,20);
        AddressText.setLocation(750, 350);
        add(AddressText);

        JLabel Nationality = new JLabel("Nationality");
        Nationality.setSize(100,20);
        Nationality.setLocation(650,400);
        add(Nationality);

        NationalityText = new JTextField();
        NationalityText.setSize(190,20);
        NationalityText.setLocation(750,400);
        add(NationalityText);

        JLabel Income = new JLabel("Monthly Income");
        Income.setSize(100,20);
        Income.setLocation(650,450);
        add(Income);

        IncomeText = new JTextField();
        IncomeText.setSize(190,20);
        IncomeText.setLocation(750, 450);
        add(IncomeText);

        JLabel famSize = new JLabel ("Family Size");
        famSize.setSize(100,20);
        famSize.setLocation(650, 500);
        add(famSize);

        FamSizeText = new JTextField();
        FamSizeText.setSize(190,20);
        FamSizeText.setLocation(750, 500);
        add(FamSizeText);


        JLabel Password = new JLabel ("Password");
        Password.setSize(100,20);
        Password.setLocation(650, 550);
        add(Password);

        PasswordText = new JTextField();
        PasswordText.setSize(190,20);
        PasswordText.setLocation(750, 550);
        add(PasswordText);

        Submit = new JButton("Submit");
        Submit.setSize(100, 20);
        Submit.setLocation(770, 620);
        Submit.addActionListener(this);
        add(Submit);
       
    }

    @Override
    public void actionPerformed(ActionEvent e) {


        if(e.getSource() == Submit){

            Receiver receiver = new Receiver();
            
            try {
                
                if (receiver.checkDuplicates(ICText.getText(),AddressText.getText()) == 0) {
                    if(Male.isSelected() && Female.isSelected() ){
                        JOptionPane.showMessageDialog(this, " Please select one gender only!", "Alert",JOptionPane.WARNING_MESSAGE);
                    }
                    else if(Male.isSelected()){
                        receiver.saveReceiverReg("R",ICText.getText(), PasswordText.getText(), NameTextField.getText()
                                                ,"Male", EmailText.getText(),ContacTextField.getText(),
                                                AddressText.getText(), NationalityText.getText(), Double.parseDouble(IncomeText.getText()), Integer.parseInt(FamSizeText.getText()));

                        JOptionPane.showMessageDialog(this, "Your registration has been successfully created.", "Successful",JOptionPane.INFORMATION_MESSAGE);
                        switcher.show("login");
                    }

                    else if (Female.isSelected()){
                        receiver.saveReceiverReg("R",ICText.getText(), PasswordText.getText(), NameTextField.getText()
                                        ,"Female", EmailText.getText(),ContacTextField.getText(),
                                        AddressText.getText(), NationalityText.getText(), Double.parseDouble(IncomeText.getText()), Integer.parseInt(FamSizeText.getText()));
                        JOptionPane.showMessageDialog(this, "Your registration has been successfully created.", "Successful",JOptionPane.INFORMATION_MESSAGE);
                        switcher.show("login");
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "Please select your gender!", "Alert",JOptionPane.WARNING_MESSAGE);
                    }
            }  
            
            else if (receiver.checkDuplicates(ICText.getText(),AddressText.getText()) == 1){
                JOptionPane.showMessageDialog(this, "IC or Home Address are existed! Please try again", "Warning",JOptionPane.WARNING_MESSAGE);

            }
     
            }

            catch (IOException e1) {
               
                e1.printStackTrace();
            }
        
        }

    }
    
}

