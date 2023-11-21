import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.util.Date;

public class MoneyDonation extends JPanel implements ActionListener {
    JButton rm10, rm100, rm50, rm20, custom, proceed, upload;
    JTextField amountTF, nameTF, emailTF, addTF,receipt;
    JCheckBox anonymous;
    private Switchable switcher;
    MoneyDonation(Switchable switcher) {
        this.switcher = switcher;
        this.setLayout(null);
        JLabel title = new JLabel("EVERY  RM5  YOU  DONATE  PROVIDES  1  MEAL  TO  SOMEONE  IN  NEED");
        title.setSize(1500, 50);
        title.setLocation(20, 0);
        title.setFont(new Font("Ariel", Font.BOLD, 40));
        rm10 = new JButton("RM10");
        rm10.setSize(80,30);
        rm10.addActionListener(this);
        //rm10.setBackground(new Color(135,206,250));
        rm10.setLocation(150,130);
        rm20 = new JButton("RM20");
        rm20.setSize(80,30);
        rm20.addActionListener(this);
        //rm20.setBackground(new Color(135,206,250));
        rm20.setLocation(255,130);
        rm50 = new JButton("RM50");
        rm50.setSize(80,30);
        rm50.addActionListener(this);
        //rm50.setBackground(new Color(135,206,250));
        rm50.setLocation(360,130);
        rm100 = new JButton("RM100");
        rm100.setSize(80,30);
        rm100.addActionListener(this);
        //rm100.setBackground(new Color(135,206,250));
        rm100.setLocation(465,130);
        custom = new JButton("Custom");
        custom.setSize(80,30);
        custom.addActionListener(this);
       // custom.setBackground(new Color(135,206,250));
        custom.setLocation(570,130);
        JPanel account = new JPanel();
        account.setLayout(null);
        account.setBounds(750,100,800,600);
        account.setBackground(new Color(230,230,250));
        JLabel amountLbl = new JLabel("Enter an amount: ");
        amountLbl.setFont(new Font("Ariel", Font.BOLD,15));
        amountTF = new JTextField();
        amountLbl.setLocation(150,200);
        amountLbl.setSize(200,30);
        amountTF.setSize(200,30);
        amountTF.setLocation(450,200);
        JLabel personalDetailsLbl = new JLabel ("Personal Details: ");
        personalDetailsLbl.setFont(new Font("Ariel", Font.BOLD, 23));
        personalDetailsLbl.setSize(200,30);
        personalDetailsLbl.setLocation(150,250);
        JLabel nameLbl = new JLabel ("Full Name");
        nameTF = new JTextField();
        nameLbl.setSize(100,30);
        nameLbl.setFont(new Font("Ariel", Font.BOLD,15));
        nameLbl.setLocation(150,320);
        nameTF.setSize(200,30);
        nameTF.setLocation(450,320);
        JLabel emailLbl = new JLabel ("Email: ");
        emailTF = new JTextField();
        emailLbl.setFont(new Font("Ariel", Font.BOLD,15));
        emailLbl.setSize(100,30);
        emailLbl.setLocation(150,380);
        emailTF.setSize(200,30);
        emailTF.setLocation(450,380);
        JLabel addressLbl = new JLabel ("Address");
        addressLbl.setFont(new Font("Ariel", Font.BOLD,15));
        addTF = new JTextField();
        addressLbl.setSize(100,30);
        addressLbl.setLocation(150,440);
        addTF.setSize(200,30);
        addTF.setLocation(450,440);
        anonymous = new JCheckBox("Make an anonymous donation");
        anonymous.setFont(new Font("Ariel", Font.BOLD,15));
        anonymous.setSize(300,20);
        anonymous.setLocation(150,630);
        //JLabel pymtDetails = new JLabel("<html> <h1>     Make Payment To: <br> Bank Account Holder Name: Food Bank Distribution Organization <br> Bank Account Number: <br> 900 100 3454");
        JLabel pymtDetails = new JLabel("MANUAL DONATION");
        pymtDetails.setSize(400,20);
        pymtDetails.setLocation(5,0);
        pymtDetails.setFont(new Font("BOLD", Font.BOLD, 20));
        account.add(pymtDetails);
        JLabel describe1 = new JLabel("<html><body>Donors may kindly approach any nearest CIMB branch for making their donations or <br> &emsp;&emsp;&emsp;&ensp;&emsp;&emsp; contributions through Cash Transfer or by Online Banking to:</body></html>");
        describe1.setFont(new Font("Serif", Font.PLAIN, 20));
        describe1.setSize(900,55);
        describe1.setLocation(20,50);
        account.add(describe1);
        JLabel accName = new JLabel("MMU Foodbank Distribution Organization", SwingConstants.CENTER);
        accName.setFont(new Font("BOLD", Font.BOLD, 30));
        accName.setSize(650,50);
        accName.setLocation(60,200);
        account.add(accName);
        JLabel accNum = new JLabel("900 100 3454", SwingConstants.CENTER);
        accNum.setFont(new Font("BOLD", Font.BOLD, 30));
        accNum.setSize(200,30);
        accNum.setLocation(300,250);
        account.add(accNum);
        JLabel bankName = new JLabel("CIMB Bank", SwingConstants.CENTER);
        bankName.setFont(new Font("BOLD", Font.BOLD, 30));
        bankName.setSize(200,30);
        bankName.setLocation(300,300);
        account.add(bankName);

        JLabel describe2 = new JLabel("<html><body>*TAX EXEMPTION APPLICATION* <br><br><br>If you make a donation manually, please fill out the form to claim the tax exemption. <br><br>Make sure you keep proof of donations for our reference.<br> <br>Please fill out the form.</body></html>");
        describe2.setFont(new Font("Ariel", Font.PLAIN, 15));
        describe2.setSize(900,200);
        describe2.setLocation(20,400);
        account.add(describe2);
        
        JLabel uploadLbl = new JLabel("Upload Proof of Payment (jpg)");
        uploadLbl.setFont(new Font("Ariel", Font.BOLD,15));
        receipt = new JTextField();
        uploadLbl.setSize(400,30);
        uploadLbl.setLocation(150,500);
        upload = new JButton("UPLOAD");
        upload.setSize(100,30);
        upload.addActionListener(this);
        upload.setLocation(450,550);
        receipt.setSize(200,30);
        receipt.setLocation(450,500);

        proceed = new JButton ("PROCEED");
        proceed.setSize(100,30);
        proceed.setLocation(350,690);
        proceed.addActionListener(this);

        add(title);
        add(amountLbl);
        add(amountTF);
        add(personalDetailsLbl);
        add(nameLbl);
        add(nameTF);
        add(emailLbl);
        add(emailTF);
        add(addressLbl);
        add(addTF);
        add(anonymous);
        add(uploadLbl);
        add(receipt);
        add(proceed);
        add(account);
        add(rm10);
        add(rm20);
        add(rm50);
        add(rm100);
        add(custom);
        add(upload);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
       if (e.getSource() == rm10){
           amountTF.setText("10");
       }

       else if (e.getSource() == rm20){
        amountTF.setText("20");
        }

        else if (e.getSource() == rm50){
            amountTF.setText("50");
        }
        else if (e.getSource() == rm100){
            amountTF.setText("100");
        }

        else if(e.getSource() == custom){
            amountTF.setText("");
        }

        else if(e.getSource() == proceed){
            if (receipt.getText().equals(""))
                JOptionPane.showMessageDialog(this, "Please upload proof of payment!");
            else if (nameTF.getText().equals("") || emailTF.getText().equals("") || amountTF.getText().equals("") || addTF.getText().equals(""))
                JOptionPane.showMessageDialog(this, "Please enter all the details!");
            else {
                String donationID = "D" + (new Date()).getTime();
                Donation donor = new Donation();
                if(anonymous.isSelected())
                    donor.saveDonation(donationID, nameTF.getText(), emailTF.getText(), addTF.getText(), Double.parseDouble(amountTF.getText()), true);
                else
                    donor.saveDonation(donationID, nameTF.getText(), emailTF.getText(), addTF.getText(), Double.parseDouble(amountTF.getText()), false);
                JOptionPane.showMessageDialog(null, "Thank you for your donation!");
                nameTF.setText("");
                emailTF.setText("");
                addTF.setText("");
                amountTF.setText("");
                receipt.setText("");
                anonymous.setSelected(false);
            }
            
        }

        else if (e.getSource() == upload){
            JFileChooser fc = new JFileChooser();
            int result = fc.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            String filename = file.getAbsolutePath();
            receipt.setText(filename);
     
        }
        else{
            JOptionPane.showMessageDialog(null, "No file choosen");
        }
        }


        
    }

}