package components;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import GUI.DrivingGameFrame;

/**
 * Mediator - a mediator for Moked, BigBrother (singletone),Driver and State (whith DrivingGameFrame)
 * @version 1.4 6 June 2020
 * @author Guy Cohen 205579808,Yarin Avraham 208401166
 * @see Moked
 * @see BigBrother
 * @see State
 * @see DrivingGameFrame (using set/getState)
 */

public class Mediator {
    private Moked moked = Moked.getMoked();
    private static ArrayList<Driver> driversThatGotReports = new ArrayList<>();
    private static State state;

    /**
     * send report (from bigBrother) to moked (to write - locked)
     * @param report
     */
    public void sendReportToMoked(String report) {
        moked.writeReport(report);
        sendMessageToDriver(report);
    }

    /**
     * send report (from moked) to driver and add it to an ArrayList that contain all the drivers that have a report 
     * @param report
     */
    public void sendMessageToDriver(String report) {
        driversThatGotReports
                .add(new Driver(BigBrother.getBigBrother().getVehicleIdByReport(report), moked.getFileDirection()));
        driversThatGotReports.get(driversThatGotReports.size() - 1).start();
    }

    /**
     * funtion for the Driver to read report (unlocked)
     * @param driver
     */
    public void readReportFromeFile(Driver driver) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(driver.getFileDirection())); // Set true for append mode);
            String report = reader.readLine();
            while (report != null && !report.contains("vehicle id - " + driver.getVehicleId())) {
                // read next line
                report = reader.readLine();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * send an aprovement to moked
     * @param aprovement
     */
    public void sendReadAprove(String aprovement){ moked.printReadAprovment(aprovement);}

    /**
     * use in DrivingGameFrame - change state - can/'t exit with menu exit button
     */
    public static void setState() {
        for (Driver driver : driversThatGotReports)
            if (!driver.HasReaad()) {
                state = new CanNotExitState();
                return;
            }
        state = new CanExitState();
    }

    /**
     * use in DrivingGameFrame
     * @return state
     */
    public static State getState() {
        return state;
    }
    
}