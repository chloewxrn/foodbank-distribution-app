import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

public class MainGui extends JFrame implements Switchable, ActionListener {
    JFrame frame = new JFrame ("FoodBank Distribution APP");
    CardLayout layout = new CardLayout(20,20);
    JPanel mainPanel = new JPanel(layout);
    ReceiverProfile profilePanel;
    ReceiverUpdateProfile updateProfile;
    RegisterForm register;
    ReviewRegistration review;
    editDistributionInfo FoodDistributionInfo;
    viewDistributionInfo viewDistributionInfoPanel;
    MoneyDonation donation;
    ManageFoodContent foodContent;
    FeedbackView feedbackViewReceiver;
    FeedbackViewUser feedbackViewUser;
    FoodCategory foodCat;
    ViewFoodItemStatus itemStatus;
    ReportGenerate reportGenerate;

   // AddAdminPanel adminPanel;
   // ReportGenerate reportPanel;
   // AgentPropertyList agentPropertyList;
    //AdminPropertyPanel adminPropertyPanel;
   // HomePanel homeTT;



    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
    private JMenuBar menuBar;
    private static JMenu adminBar,receiverBar,userBar;
    // Receiver's menuItems
    private JMenuItem editProfile,logout, reportReceiver, profileTenant, viewDistribution, feedbackReceiver, viewFoodCategory;
    // 
    private JMenuItem home;
    // Addtional
    // User's Menu Item
    private JMenuItem loginUser, reportUser, homeUser, foodStatusUser, feedbackUser;
    // admin menu
    private JMenuItem adminReview, homeAdmin, reportAdmin, receiverlist, logoutAdmin, distributionInfo, food, feedbackAdmin;


    public MainGui() {

        menuBar = new JMenuBar();
        menuBar.add(Box.createHorizontalGlue());
        userBar = new JMenu("≡ User");
        menuBar.add(userBar);
        loginUser = new JMenuItem("Login");
        homeUser = new JMenuItem("Home");
        reportUser = new JMenuItem("Report");
        foodStatusUser = new JMenuItem("Food Items Status");
        feedbackUser = new JMenuItem("Feedback");
        loginUser.addActionListener(this);
        reportUser.addActionListener(this);
        homeUser.addActionListener(this);
        foodStatusUser.addActionListener(this);
        feedbackUser.addActionListener(this);
        userBar.add(loginUser);
        userBar.add(homeUser);
        userBar.add(foodStatusUser);
        userBar.add(reportUser);
        userBar.add(feedbackUser);
     
        userBar.setVisible(true);

        // Admin Bar
        adminBar = new JMenu("≡ Admin");
        menuBar.add(adminBar);
        adminBar.setVisible(false);
        adminReview = new JMenuItem("Review Registration");
       // addAdmin = new JMenuItem("Add new admin");
        homeAdmin = new JMenuItem("Home");
        reportAdmin = new JMenuItem("Report");
        logoutAdmin = new JMenuItem("Logout");
        receiverlist = new JMenuItem("Receiver List");
        distributionInfo = new JMenuItem("Distribution Info");
        food = new JMenuItem("Food Content");
        feedbackAdmin = new JMenuItem("Feedback");


        receiverlist.addActionListener(this);
        logoutAdmin.addActionListener(this);
      //  addAdmin.addActionListener(this);
        adminReview.addActionListener(this);
        homeAdmin.addActionListener(this);
        reportAdmin.addActionListener(this);
        distributionInfo.addActionListener(this);
        food.addActionListener(this);
        feedbackAdmin.addActionListener(this);
        adminBar.add(homeAdmin);
        adminBar.add(reportAdmin);
        adminBar.add(distributionInfo);
        adminBar.add(adminReview);
        adminBar.add(food);
        adminBar.add(receiverlist);
        adminBar.add(feedbackAdmin);
        adminBar.add(logoutAdmin);

        // Receiver Bar
        receiverBar = new JMenu("≡ Receiver");
        menuBar.add(receiverBar);
        receiverBar.setVisible(false);
        profileTenant = new JMenuItem("Profile");
        profileTenant.addActionListener(this);
        reportReceiver = new JMenuItem("Report");
        reportReceiver.addActionListener(this);
        feedbackReceiver = new JMenuItem("Feedback");
        feedbackReceiver.addActionListener(this);
        logout = new JMenuItem("Logout");
        logout.addActionListener(this);
        home = new JMenuItem("Home");
        home.addActionListener(this);
        viewDistribution = new JMenuItem("View Distribution Info");
        viewDistribution.addActionListener(this);
        viewFoodCategory = new JMenuItem("Food Application");
        viewFoodCategory.addActionListener(this);


   
        receiverBar.add(home);
        receiverBar.add(profileTenant);
        // tenantBar.add(editProfileTenant);
        receiverBar.add(reportReceiver);
        receiverBar.add(viewFoodCategory);
        receiverBar.add(viewDistribution);
        receiverBar.add(feedbackReceiver);
        receiverBar.add(logout);

        frame.add(menuBar);
        frame.setJMenuBar(menuBar);
        menuBar.setVisible(true);
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
        LoginPanel loginPanel = new LoginPanel(this);
        mainPanel.add(loginPanel, "login");
        
        review = new ReviewRegistration();
        mainPanel.add(review, "review");
        Home home = new Home(this);
        mainPanel.add(home, "home");   
        updateProfile = new ReceiverUpdateProfile();
        mainPanel.add(updateProfile, "updateProf");
        ViewReceiverList receiverList;
        try {
            receiverList = new ViewReceiverList();
            mainPanel.add(receiverList, "receivers");
        } catch (IOException e) {
           
            e.printStackTrace();
        }
        donation = new MoneyDonation(this);
        mainPanel.add(donation, "donation");

        viewDistributionInfoPanel = new viewDistributionInfo(this);
        mainPanel.add(viewDistributionInfoPanel, "viewDistributionInfo");

        register = new RegisterForm(this);
        mainPanel.add(register, "register");

        FoodDistributionInfo = new editDistributionInfo();
        mainPanel.add(FoodDistributionInfo, "FoodDistributionInfo");

        foodContent = new ManageFoodContent();
        mainPanel.add(foodContent, "FoodContent");

        feedbackViewReceiver = new FeedbackView();
        mainPanel.add(feedbackViewReceiver, "feedbackViewReceiver");

        feedbackViewUser = new FeedbackViewUser();
        mainPanel.add(feedbackViewUser, "feedbackViewUser");

        reportGenerate = new ReportGenerate();
        mainPanel.add(reportGenerate, "reportGenerate");

        try {
            foodCat = new FoodCategory();
            mainPanel.add(foodCat, "FoodCategory");
            itemStatus = new ViewFoodItemStatus();
            mainPanel.add(itemStatus, "FoodStatus");

        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

       ((CardLayout) mainPanel.getLayout()).show(mainPanel, "home");
      




        frame.getContentPane().add(mainPanel);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        frame.setVisible(true);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
    }


    @Override
    public void show (String card) {
        ((CardLayout) mainPanel.getLayout()).show(mainPanel, card);
    }


   
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource().equals(home) || e.getSource().equals(homeAdmin) || e.getSource().equals(homeUser) ){
            show("home");
        }
        else if (e.getSource().equals(foodStatusUser))
            show("FoodStatus");

        else if (e.getSource().equals(loginUser)){
            show("login");
        }

        else if(e.getSource().equals(editProfile)){
            show("updateProf");
        }   

        else if(e.getSource().equals(profileTenant)){
            show("profile");
        }

        else if(e.getSource().equals(receiverlist)){
            show("receivers");
        }
        
        else if (e.getSource().equals(adminReview))
            show("review");
      
        else if (e.getSource().equals(reportAdmin) || e.getSource().equals(reportUser) || e.getSource().equals(reportReceiver)){
            show("reportGenerate");
        }

        else if (e.getSource().equals(food)){
            show("FoodContent");
        }

        else if(e.getSource().equals(distributionInfo)){
            show("FoodDistributionInfo");
        }

        
        else if(e.getSource().equals(viewDistribution)){
            show("viewDistributionInfo");
        }


        else if (e.getSource().equals(feedbackReceiver)) {
            show("feedbackViewReceiver");
        }

        else if (e.getSource().equals(feedbackUser) || e.getSource().equals(feedbackAdmin)) {
            show("feedbackViewUser");
        }

        else if (e.getSource().equals(viewFoodCategory)) {
            show("FoodCategory");
        }



        else if (e.getSource().equals(logout) || e.getSource().equals(logoutAdmin) || e.getSource().equals(logout)){
            frame.dispose();
            new MainGui();
        }
    }
    
    @Override
    public void setReceiver (Receiver receiver) {
        profilePanel = new ReceiverProfile(this);
        profilePanel.receive(receiver);
        updateProfile.receive(receiver);
        foodCat.receive(receiver);
        
        show("home");


        int result;
        try {
            result = receiver.checkApplicationExist(receiver);
            if(result == 1){
                ArrayList<String> alertNotification = new ArrayList<>();
                try {
                    alertNotification = receiver.distributionInfoAlertNotification(receiver);
                    viewDistributionInfoPanel.receive(receiver);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                // Application ID, Receiver ID, Category, Date, Time, Location
                String notification = "Application ID : " + alertNotification.get(0) + 
                                      "\nDate : "  + alertNotification.get(3) + 
                                      "\nTime : " + alertNotification.get(4) + 
                                      "\nLocation : " + alertNotification.get(5);
    
                                      JOptionPane.showMessageDialog(mainPanel,notification,"Food Distribution Notification",JOptionPane.WARNING_MESSAGE);  
            }


        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
        
       
        mainPanel.add(profilePanel, "profile");
        //show("profile");
        assignMenu("receiver");
    }

    @Override
    public void setAdmin (Admin admin) {
        review.receive(admin);
        show("home");
        assignMenu("admin");
    }

    // Additional 
    public static void assignMenu(String role){
        if(role.equals("user")){
            adminBar.setVisible(false);
            receiverBar.setVisible(false);
            userBar.setVisible(false);
         }

        else if(role.equals("admin")){
            adminBar.setVisible(true);
            userBar.setVisible(false);  }

        else if(role.equals("receiver")){
            receiverBar.setVisible(true); 
            userBar.setVisible(false);  }

        }

    

    public static void main (String[] args) {
        new MainGui();   
    }
}


