import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FeedbackView extends JPanel implements ActionListener{

    DefaultTableModel tableModel;
    JTable table = new JTable();
    String[] columnName = {"Feedback"};
    JTextArea feedbackInput;
    JScrollPane feedbackInputScroll, feedbackDisplayScroll;
    JButton writeFeedbackBtn;
    JPanel writeFeedbackPane, displayFeedbackPane;
    JComboBox<String> feedbackTitle;
    String[] title = {"Select a title", "Food", "Others"};
    Feedback feedback;

    
    public FeedbackView() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        displayFeedbackPane = createDisplayFeedbackPane();
        feedbackDisplayScroll = new JScrollPane(displayFeedbackPane);
        feedbackDisplayScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        feedbackDisplayScroll.setBorder(BorderFactory.createTitledBorder("Feedback"));

        writeFeedbackBtn = new JButton("Write Your Feedback");
        writeFeedbackBtn.addActionListener(this);

        gbc.ipadx = 600;
        gbc.ipady = 650;
        gbc.gridy = 0;
        add(feedbackDisplayScroll, gbc);

        gbc.insets = new Insets(10, 0, 0, 0);
        gbc.ipadx = 0;
        gbc.ipady = 0;
        gbc.gridy = 1;
        add(writeFeedbackBtn, gbc);
        
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


    // popup a panel for user to write their feedback
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == writeFeedbackBtn) {
            createWriteFeedbackPane();
            String[] option = {"Submit"};

            int submit_option = JOptionPane.showOptionDialog(this, writeFeedbackPane, "Write Your Feedback", 
                    JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE, null, option, option[0]);

            if (submit_option == JOptionPane.OK_OPTION) {
                LocalDateTime lDateTime = LocalDateTime.now();
                DateTimeFormatter idFormatter = DateTimeFormatter.ofPattern("ddMMyyHHmmss");
                String feedbackId = "FB" + idFormatter.format(lDateTime);

                feedback = new Feedback(feedbackId, feedbackTitle.getSelectedItem().toString(), feedbackInput.getText());

                String toFeedbackString = feedback.toFeedbackCSVString(lDateTime);
                feedback.saveToCSV(toFeedbackString);
                
                feedbackDisplayScroll.remove(displayFeedbackPane);
                displayFeedbackPane = createDisplayFeedbackPane();
                feedbackDisplayScroll.add(displayFeedbackPane);

                JOptionPane.showMessageDialog(this, "Thank you for your feedback.");
                
            }
        }
    }


    // create a write feedback panel
    private void createWriteFeedbackPane() {
        writeFeedbackPane = new JPanel(new GridBagLayout());
        writeFeedbackPane.setBorder(BorderFactory.createTitledBorder("Feel free to give us your feedback"));
        GridBagConstraints gbc = new GridBagConstraints();
        
        feedbackTitle = new JComboBox<>(title);

        feedbackInput = new JTextArea("Feedback");
        feedbackInput.setLineWrap(true); // go to next line while reach the TextArea width
        feedbackInput.setWrapStyleWord(true); // wrap by spaces instead of letters

        feedbackInputScroll = new JScrollPane(feedbackInput);
        feedbackInputScroll.setPreferredSize(new Dimension(30, 300));
        feedbackInputScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 10;
        gbc.ipadx = 300;
        gbc.insets = new Insets(15, 15, 15, 15);

        int locationY = 0;
        gbc.gridy = locationY;
        writeFeedbackPane.add(feedbackTitle, gbc);

        locationY++;
        gbc.gridy = locationY;
        writeFeedbackPane.add(feedbackInputScroll, gbc);
    }
}