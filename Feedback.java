import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Feedback {
    protected String feedbackId, feedbackTitle;
    protected String feedbackDescrip;


    public Feedback() {}


    public Feedback(String fbId, String fbTitle, String fbDescrip) {
        feedbackId = fbId;
        feedbackTitle = fbTitle;
        feedbackDescrip = fbDescrip;
    }


    // convert the details of feedback into String
    protected String toFeedbackCSVString(LocalDateTime lDateTime) {
        DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mma");
        String strDateTime = dtFormatter.format(lDateTime);

        return feedbackId +";"+ feedbackTitle +";"+ feedbackDescrip +";"+ strDateTime;
    }


    // save into csv
    protected void saveToCSV(String toFeedbackString) {
        try {
            BufferedWriter bWriter = new BufferedWriter(new FileWriter("Feedback.csv", true));

            bWriter.write(toFeedbackString + "\n");

            bWriter.close();
        }catch (Exception e) { e.printStackTrace(); }
    }
}
