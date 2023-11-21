import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Home extends JPanel implements ActionListener{
    JLabel pic;
    Timer timer;
    int x = 0;
    int z = 0;
    int y = 65;
    
    JButton donate;
    private Switchable switcher;

    String[] picList = { "foodbank2.png","foodbank6.jpg","foodbank8.jpg","foodbank1.jpg","foodbank4.jpg", "foodbank5.jpg","foodbank3.jpg","foodbank7.jpg","foodbank0.jpg"};
    
    public Home(Switchable switcher){
        this.switcher = switcher;
     
        donate = new JButton("Donate");
        donate.setFont(new Font("Times", Font.BOLD, 15));
        donate.setBackground(new Color(0,102,0));
        donate.setForeground(Color.WHITE);
        donate.setSize(100,40);
        donate.setLocation(1370, 0);
        donate.addActionListener(this);

        BufferedImage donation, household, warehouse, contact;
        try {
            donation = ImageIO.read(this.getClass().getResource("donation.png"));
            household = ImageIO.read(this.getClass().getResource("household.png"));
            warehouse = ImageIO.read(this.getClass().getResource("warehouse.png"));
            contact = ImageIO.read(this.getClass().getResource("phone.png"));
            JLabel donateIcon = new JLabel(new ImageIcon(donation));
            JLabel householdIcon = new JLabel(new ImageIcon(household));
            JLabel warehouseIcon = new JLabel(new ImageIcon(warehouse));
            JLabel phone = new JLabel(new ImageIcon(contact));
            donateIcon.setSize(65,65);
            donateIcon.setLocation(200, 550);
            householdIcon.setSize(65,65);
            householdIcon.setLocation(520,550);
            phone.setSize(65,65);
            phone.setLocation(825, 550);
            warehouseIcon.setSize(65,65);
            warehouseIcon.setLocation(1250,550);
            

            add(donateIcon);
            add(householdIcon);
            add(warehouseIcon);
            add(phone);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        pic = new JLabel();
        pic.setBounds(0, 80, 1550, 440);

        SetImageSize(8);

        JLabel totalDonation = new JLabel("2,320,120");
        JLabel RM = new JLabel("Ringgit Malaysia (RM) Worth of food");
        totalDonation.setFont(new Font("BOLD", Font.BOLD, 30));
        totalDonation.setLocation(160,620);
        totalDonation.setSize(200,30);
        RM.setFont(new Font("BOLD", Font.BOLD, 15));
        RM.setSize(400,20);
        RM.setLocation(100,660);
        add(totalDonation);
        add(RM);

        JLabel totalHouseHold = new JLabel("26,999");
        JLabel fed = new JLabel("Households Fed");
        totalHouseHold.setFont(new Font("BOLD", Font.BOLD, 30));
        totalHouseHold.setLocation(508,620);
        totalHouseHold.setSize(100,30);
        fed.setFont(new Font("BOLD", Font.BOLD, 15));
        fed.setSize(400,20);
        fed.setLocation(500,660);
        add(totalHouseHold);
        add(fed);

        JLabel contactUs = new JLabel("Contact Us");
        JLabel phoneNum = new JLabel("03-0011223");
        contactUs.setFont(new Font("BOLD", Font.BOLD, 30));
        contactUs.setLocation(798,620);
        contactUs.setSize(200,30);
        phoneNum.setFont(new Font("BOLD", Font.BOLD, 15));
        phoneNum.setSize(200,20);
        phoneNum.setLocation(830,660);
        add(contactUs);
        add(phoneNum);

        JLabel Address = new JLabel("Foodbank Warehouse");
        JLabel line1 = new JLabel("No 2, Jln MMU 09, 62000");
        JLabel line2 = new JLabel("Cyberjaya, Selangor");
        Address.setFont(new Font("BOLD", Font.BOLD, 30));
        Address.setLocation(1140,620);
        Address.setSize(400,30);
        line1.setFont(new Font("BOLD", Font.BOLD, 15));
        line1.setSize(400,20);
        line1.setLocation(1210,660);
        line2.setFont(new Font("BOLD", Font.BOLD, 15));
        line2.setSize(200,20);
        line2.setLocation(1225,680);
        add(Address);
        add(line1);
        add(line2);

      
        timer = new Timer(1600,new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                SetImageSize(x);
                x += 1;
                if(x >= picList.length )
                    x = 0; 
            }
        });
        
        add(pic);
        add(donate);
        timer.start();
        setLayout(null);
      
    }

     public void paint(Graphics gp){
        super.paint(gp);
        Graphics2D title= (Graphics2D) gp;
        title.setColor(Color.BLACK);
        title.setFont(new Font("BOLD", Font.BOLD, 34));
        title.drawString("Welcome To MMU Foodbank Distribution App", z, y);
        try {
            Thread.sleep(200);
            z += 20;
            if(z > getWidth())
               z = 0;
        repaint();
                      
        } catch (InterruptedException ex) {
            JOptionPane.showMessageDialog(this, ex);
        }
    }
    
  
    public void SetImageSize(int i){
        ImageIcon icon = new ImageIcon(picList[i]);
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(pic.getWidth(), pic.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newImg);
        pic.setIcon(newIcon);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == donate){
           // new MoneyDonation();
            switcher.show("donation");
        }

        else {
            switcher.show("FoodStatus");
        }
        
        
    }
}
