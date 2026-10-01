package pat2026;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class GetWorkers {

    private Worker[] workers = new Worker[200];
    private int count = 0;

    public GetWorkers() throws IOException {
    }

    public boolean getWorkerData() throws FileNotFoundException {

        Scanner scFile = new Scanner(new File("WorkerDetails.txt"));
        count = 0;

        while (scFile.hasNextLine()) {

            String lineOfCode = scFile.nextLine();

            if (lineOfCode.isBlank()) {
                continue;
            }

            String workerCode = lineOfCode.substring(
                    lineOfCode.indexOf(":") + 1,
                    lineOfCode.indexOf("(")
            ).trim();

            if (scFile.hasNextLine()) {
                scFile.nextLine();
            }

            String name = getValue(scFile.nextLine());
            String surname = getValue(scFile.nextLine());
            String gender = getValue(scFile.nextLine());
            String dob = getValue(scFile.nextLine());
            String age = getValue(scFile.nextLine());
            String dateStarted = getValue(scFile.nextLine());
            String serviceYears = getValue(scFile.nextLine());
            String type = getValue(scFile.nextLine());
            String role = getValue(scFile.nextLine());
            String group = getValue(scFile.nextLine());
            String wage = getValue(scFile.nextLine());
            String status = getValue(scFile.nextLine());

            if (count < workers.length) {

                workers[count] = new Worker(workerCode, name, surname, gender, dob, age, dateStarted, serviceYears, role, type, group, wage, status);

                count++;
            }
        }

        scFile.close();

        return count > 0;
    }

    private String getValue(String line) {
        return line.substring(line.indexOf(":") + 1).trim();
    }

    public Worker[] getWorkers() {
        return workers;
    }

    public int getCount() {
        return count;
    }
}
