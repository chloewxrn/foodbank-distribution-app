import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.awt.*;


public class LoginPanel extends JPanel implements ActionListener {
    
    JTextField username = new JTextField();
    JPasswordField password = new JPasswordField();
    JPanel login;
    private Switchable switcher;

    LoginPanel (Switchable switcher) {
        //to allow to switch between panel
        this.switcher = switcher;
        login = new JPanel();
        login.setBounds(530, 150, 500, 420);
        login.setLayout(null);
        login.setBackground(new Color(0f,0f,0f,.4f ));

        JLabel title = new JLabel("<html><h1> FOODBANK DISTRIBUTION APP</h1></html>");
        title.setBounds(60, 20, 600, 50);
        Font font = new Font("Times", Font.BOLD, 40);
        title.setFont(font);
        title.setForeground(Color.WHITE);
        JLabel usernameLabel = new JLabel("IC / PASSPORT");
        usernameLabel.setBounds(90, 100, 150, 30);
        usernameLabel.setForeground(Color.white);
        username.setBounds(190, 100, 200, 30);
        JLabel passLabel = new JLabel("PASSWORD");

        passLabel.setBounds(90, 180, 100, 30);
        passLabel.setForeground(Color.white);
        password.setBounds(190, 180, 200, 30);
        this.setLayout(null);
        login.add(title, BorderLayout.NORTH);
        login.add(usernameLabel);
        login.add(username);
        login.add(passLabel);
        login.add(password);

        JButton loginBtn = new JButton("Log in");
        loginBtn.setBounds(225, 250, 90, 25);
        loginBtn.setForeground(Color.BLACK);
        loginBtn.addActionListener(this);
       login.add(loginBtn);

        JLabel noAcc = new JLabel("Does not have an account? ");
        noAcc.setForeground(Color.lightGray);
        noAcc.setBounds(100, 330, 200, 25);
        login.add(noAcc);
        JButton registerBtn = new JButton("Register");
        registerBtn.setBounds(270, 330, 100, 25);
        registerBtn.setBackground(new Color(173,216,230));
        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent evt) {
                switcher.show("register");
            }
        });
        login.add(registerBtn);

        add(login, BorderLayout.CENTER);
    }

    @Override
     protected void paintComponent(Graphics g) {
        ImageIcon img = new ImageIcon("login.jpg");
        g.drawImage(img.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);

     }


    @Override
    public void actionPerformed (ActionEvent evt) {
        String userTxt = username.getText();
        char[] passTxt = password.getPassword();
        String pass = new String (passTxt);
        boolean flag = false;
        try {
            List<String> lines = Files.readAllLines(Paths.get("User.txt"));
            

            for (int i = 0; i < lines.size(); i++) {
                String[] items = lines.get(i).split(";");
                if (userTxt.equals(items[1])) {
                    flag = true;
                    if (pass.equals(items[2])) {
                        JOptionPane.showMessageDialog(null, "Login Successful");
                        if (items[0].charAt(0) == 'R') {
                            Receiver receiver = new Receiver(items[0], items[1], items[2], items[3], items[4], items[5], items[6], items[7]
                                                            , items[8], Double.parseDouble(items[9]) ,Integer.valueOf(items[10]));
                            switcher.setReceiver(receiver);
                        }
                     
                        else {
                           Admin admin = new Admin(items[0], items[1], items[2], items[3], items[4]);
                           switcher.setAdmin(admin);
                        }
                    }
                    else
                        JOptionPane.showMessageDialog(null, "Incorrect Password");
                }
            }
                if (!flag)
                    JOptionPane.showMessageDialog(null, "The username you entered does not exist");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    

}
