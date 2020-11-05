package components;

import java.io.File;
import java.util.Random;

/**
 * Driver - a vehicle driver
 * @version 1.4 7 June 2020
 * @author Guy Cohen 205579808,Yarin Avraham 208401166
 * @see Mediator
 */

public class Driver extends Thread {
    private int vehicleId;
    private String fileDirection;
    private Mediator mediator = new Mediator();
    private File fileSrc;
    private boolean hasReaad = false;

    /**
     * Constructor
     * @param vehicleId
     * @param fileDirection
     */
    public Driver(int vehicleId, String fileDirection) {
        this.vehicleId = vehicleId;
        this.fileDirection = fileDirection;
        fileSrc = new File(fileDirection);
    }

    @Override
    public void run() {
        try {
            sleep((long) (new Random()).nextInt(500));
            readReport();
        } catch (InterruptedException e) { }
    }
    
    /**
     * read report ant send an aprove for it (->Mediator -> Moked)
     */
    public void readReport(){
        mediator.readReportFromeFile(this);
        hasReaad = true;
        mediator.sendReadAprove("Driver of vehicle number " + vehicleId + " gives his approval of the report !");
    }

    public String getFileDirection() {
        return fileDirection;
    }

    public File getFileSrc() {
        return fileSrc;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public boolean HasReaad() {
        return hasReaad;
    }
}
