package pat2026;
 
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
 
public class DeleteWorkday {
    
    private String keptText = "";
    private boolean found = false;
    private boolean skipping = false;
    
    public boolean deleteWorkday(String jobID) throws IOException {
        jobID = jobID.trim();
 
        Scanner scFile = new Scanner(new File("WorkdayDetails.txt"));
        while (scFile.hasNextLine()) {
            String line = scFile.nextLine();
 
            if (line.startsWith("JobID: ")) {
                skipping = line.startsWith("JobID: " + jobID + "\t");
                if (skipping) {
                    found = true;
                }
            }
 
            if (skipping == false) {
                keptText += line + "\n";
            }
        }
        scFile.close();
 
        if (found) {
            PrintWriter writer = new PrintWriter(new FileWriter("WorkdayDetails.txt", false));
            writer.print(keptText);
            writer.close();
        }
 
        return found;
    }
}