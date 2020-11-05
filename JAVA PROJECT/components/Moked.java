package components;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * Moked - singeletone class, write (ReadWriteLock)
 * @version 1.4 6 June 2020
 * @author Guy Cohen 205579808,Yarin Avraham 208401166
 * @see Mediator
 * @see BigBrother
 */

public class Moked {
    private static Moked moked;
    private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private final Lock read = rwl.readLock();
    private final Lock write = rwl.writeLock();
    private final static String path = "C:\\Users\\user\\Desktop\\הנדסת תוכנה\\שנה ב סמ ב\\JAVA\\JAVA PROJECT\\";
    private final static String fileName = "reports.txt";
    private final static String fileDirection = path + fileName;
    private final File fileSrc = new File(fileDirection);
    private BufferedWriter out = null;
    private boolean isFirstReport = true;
    
    /**
     * default constructor
     */
    private Moked() {
        try {
            new FileWriter(fileSrc).flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }

    /**
     * singeltone implemation
     * @return static Moked object
     */
    public static Moked getMoked() {
        if (moked == null)
            synchronized (Moked.class) {
                if (moked == null)
                    return moked = new Moked();
            }
        return moked;
    }

    /**
     * get a report from Mediator and write it in reports.txt 
     * @param report
     */
    public void writeReport(String report) {
        try {
            write.lock();
            out = new BufferedWriter(new FileWriter(fileSrc, true)); // Set true for append mode);
            if (!isFirstReport)
                out.newLine(); // Add new line
            else
                isFirstReport = false;
            out.append(report);

            System.out.print(report);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            write.unlock();
        }
    }

    /**
     * 
     * @return pane - a JPanel with al the reports (JLables)
     */
    public static JPanel getAllReportsJPanel() {
        BufferedReader reader = null;
        String reports;
        JPanel pane = new JPanel();
        try {
            reader = new BufferedReader(new FileReader(fileDirection)); // Set true for append mode);
            reports = reader.readLine();
            pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
            while (reports != null) {
                // read next line
                pane.add(new JLabel(reports));
                reports = reader.readLine();
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
        return pane;
    }

    /**
     * print aprove (from Mediator) for reading report from driver
     * @param aprovement
     */
    public void printReadAprovment(String aprovement){
        System.out.println(aprovement);
    }

    /**
     * to send direction of reports.txt file to driver 
     * @return fileDirection 
     */
    public String getFileDirection() {
        return fileDirection;
    }

    /**
     * 
     * @return read
     */
    public Lock getRead() {
        return read;
    }
}