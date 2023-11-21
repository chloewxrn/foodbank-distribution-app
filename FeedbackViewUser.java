import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class FeedbackViewUser extends JPanel {
    DefaultTableModel tableModel;
    JTable table = new JTable();
    String[] columnName = {"Feedback"};
    JTextArea feedbackInput;
    JScrollPane feedbackInputScroll, feedbackDisplayScroll;
    JPanel writeFeedbackPane, displayFeedbackPane;
    JComboBox<String> feedbackTitle;
    String[] title = {"Select a title", "Food", "Others"};
    Feedback feedback;

    
    public FeedbackViewUser() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        displayFeedbackPane = createDisplayFeedbackPane();
        feedbackDisplayScroll = new JScrollPane(displayFeedbackPane);
        feedbackDisplayScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        feedbackDisplayScroll.setBorder(BorderFactory.createTitledBorder("Feedback"));

        gbc.ipadx = 600;
        gbc.ipady = 700;
        gbc.gridy = 0;
        add(feedbackDisplayScroll, gbc);
        
    }


    private JPanel createDisplayFeedbackPane() {
        JPanel tempFeedbackPane = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        ImageIcon userIcon = new ImageIcon("user.png");
        int locationY = 0;
        BufferedReader bReader;
        String row;

        try {
            bReader = new BufferedReader(new FileReader("Feedback.csv"));
            while( (row = bReader.readLine()) != null) {
                String[] items = row.split(";");

                gbc.gridx = 0;
                gbc.gridy = locationY;
                tempFeedbackPane.add(new JLabel(userIcon), gbc);

                gbc.gridx = 1;
                tempFeedbackPane.add(new JLabel(items[3]), gbc);

                locationY++;
                gbc.gridy = locationY;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                JLabel fbDescrip = new JLabel(items[2]);
                fbDescrip.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                tempFeedbackPane.add(fbDescrip, gbc);

                locationY++;
            }

        }catch (Exception e) { e.printStackTrace(); }

        return tempFeedbackPane;
    }
}
