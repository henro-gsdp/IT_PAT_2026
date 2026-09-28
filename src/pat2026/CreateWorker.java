package pat2026;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class CreateWorker {

    private WorkerLoginDetails code = new WorkerLoginDetails(); // Unique worker code
    private String workerCode, workerPassword;

    // Only generates the new worker's code and password
    public CreateWorker() throws IOException {
        Scanner scanCode = new Scanner(code.getWorkerCode()).useDelimiter(",");

        workerCode = scanCode.next();
        workerPassword = scanCode.next();

        scanCode.close();
    }

    // Calculates age and years of service, then adds the worker to WorkerDetails.txt
    public void saveWorker(String name, String surname, String gender, LocalDate dob,
            LocalDate startDate, String type, String role, String group,
            double wage, String status) throws IOException {

        LocalDate dateNow = LocalDate.now();
        int age = Period.between(dob, dateNow).getYears();
        int numOfYears = Period.between(startDate, dateNow).getYears();
        String wageString = String.format("%.2f", wage);
        char genderChar = gender.trim().charAt(0);

        PrintWriter workerDetails = new PrintWriter(new FileWriter("WorkerDetails.txt", true));

        workerDetails.println("WORKER CODE: " + workerCode + "\t(Password: " + workerPassword + ")\n"
                + "--------------------------------------------------------------------------------------------------\n"
                + "Worker Name: " + name + "\nWorker Surname: " + surname + "\nGender: " + genderChar
                + "\nDOB: " + dob + "\nAge: " + age + "\nDate started working on Farm: " + startDate
                + "\nNumber of years in service: " + numOfYears
                + "\nType of worker: " + type + "\nRole of worker: " + role + "\nGroup: " + group
                + "\nWage:" + wageString + "\nStatus: " + status + "\n");

        workerDetails.close();
    }

    public String getWorkerCode() {
        return workerCode;
    }
}