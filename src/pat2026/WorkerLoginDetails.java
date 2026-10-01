package pat2026;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.lang.String;

public class WorkerLoginDetails {

    private String workerCode, workerPassword;
    // private int num;

    // Data for generating code
    private int codeNum;
    private int lastNum;
    private String codeNumString, workerRole, password;

    public WorkerLoginDetails(String workerRole) throws IOException {
        this.workerRole = workerRole;
        genWorkerLoginDetails();
    }

    private void genWorkerLoginDetails() throws IOException {
        Scanner scFile = new Scanner(new File("WorkerCodes.txt"));

        while (scFile.hasNextLine()) {
            Scanner scLine = new Scanner(scFile.nextLine());

            if (scLine.hasNext()) {
                String code = scLine.next();

                String num = code.substring(3, 6);
                lastNum = Integer.parseInt(num);
            }
            scLine.close();
        }

        scFile.close();

        codeNum = lastNum + 1;
        codeNumString = String.format("%03d", codeNum);

        workerCode = workerRole.substring(0, 3).toUpperCase() + codeNumString;

        password = "#" + codeNumString;

        PrintWriter genCode = new PrintWriter(new FileWriter("WorkerCodes.txt", true));

        genCode.println(workerCode + "," + password);

        genCode.close();
    }

    public String getWorkerRole() {
        return workerRole;
    }

    public void setWorkerRole(String workerRole) {
        this.workerRole = workerRole;
    }

    public String getWorkerPassword() {
        return password;
    }

    public String getWorkerCode() throws FileNotFoundException {
        Scanner scFile = new Scanner(new File("WorkerCodes.txt"));

        while (scFile.hasNextLine()) {
            Scanner scLine = new Scanner(scFile.nextLine()).useDelimiter(",");
            workerCode = scLine.next();
            workerPassword = scLine.next();

            scLine.close();
        }

        scFile.close();
        return workerCode;
    }

}
