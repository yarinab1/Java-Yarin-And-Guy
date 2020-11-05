package GUI;

import components.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;
import java.awt.*;

/**
 * DrivingGameFrame - a JFrame to display the game.
 * @version 1.4 11 Jone 2020
 * @author Guy Cohen 205579808,Yarin Avraham 208401166
 * @see CreateRoadSystemDialog
 * @see Driving
 */

public class DrivingGameFrame extends JFrame implements ActionListener{
    private final static String TITLE = "Road system";
    private final static String CRS = "Create road system";
    private JMenuBar menuBar;
    private JMenu fileMenu, helpMenu;
    private JMenuItem exitItem, backgroundMenu, vehiclesColorMenu, helpItem,blueBackground,NoneBackground, blueCar,MageCar,OrangeCar,RandomCar;
    private JPanel buttonsPanels, buttonsPanel1, buttonsPanel2, paintPanel;
    private JButton CRSButton, startButton, stopButton, resumeButton, infoButton, buildAMap, cloneACar, Reports;
    private Driving driving = null;
    private JTable infoTable;
    private JScrollPane infoScrollPane;
    private JLayeredPane layeredPane; // contain infoTable and paintPanel
    private Color vehicleColor = Color.BLUE;
    private final int X = 800;
    private final int Y = 750; //650 (layeredPane) + buttons
    final static int Car_Length = 10;
    final static int Car_Width = 4;
    private int numOfDrivingIteration = 20;
    private JPanel reportsJPanel = null;
    
    /**
     * defualt constructor - numOfDrivingIteration stay 20
     */
    public DrivingGameFrame(){
        super(TITLE);
        setMenuBar();
        setButtonsPanels();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(X,Y);
        setMaximumSize(new Dimension(X,Y));
        setMinimumSize(new Dimension(X,Y));
        add(paintPanel);
        setVisible(true);
    }

    /**
     * constructor
     * @param numOfDrivingIteration - number of turns to play
     */
    public DrivingGameFrame(int numOfDrivingIteration){
        super(TITLE);
        this.numOfDrivingIteration = numOfDrivingIteration;
        setMenuBar();
        setButtonsPanels();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(X,Y);
        setMaximumSize(new Dimension(X,Y));
        setMinimumSize(new Dimension(X,Y));
        add(layeredPane);
        layeredPane.setEnabled(true);
        layeredPane.setVisible(true);
        setVisible(true);
    }

    /**
     * set the frame menu bar
     */
    public void setMenuBar(){
        fileMenu = new JMenu("File");
        backgroundMenu = new JMenu("Background");
        vehiclesColorMenu = new JMenu("Vehicles Color");
        helpMenu = new JMenu("Help");
        menuBar = new JMenuBar();
        exitItem = new JMenuItem("Exit");
        blueBackground = new JMenuItem("Blue");
        NoneBackground = new JMenuItem("None");
        blueCar = new JMenuItem("Blue");
        MageCar = new JMenuItem("Magenta");
        OrangeCar = new JMenuItem("Orange");
        RandomCar = new JMenuItem("Random");
        helpItem = new JMenuItem("Help");
        fileMenu.add(exitItem);
        helpMenu.add(helpItem);
        menuBar.add(fileMenu);
        menuBar.add(backgroundMenu);
        menuBar.add(vehiclesColorMenu);
        menuBar.add(helpMenu);
        backgroundMenu.add(blueBackground);
        backgroundMenu.add(NoneBackground);
        vehiclesColorMenu.add(blueCar);
        vehiclesColorMenu.add(MageCar);
        vehiclesColorMenu.add(OrangeCar);
        vehiclesColorMenu.add(RandomCar);
        exitItem.addActionListener(this);
        helpItem.addActionListener(this);
        blueBackground.addActionListener(this);
        NoneBackground.addActionListener(this);
        blueCar.addActionListener(this);
        MageCar.addActionListener(this);
        OrangeCar.addActionListener(this);
        RandomCar.addActionListener(this);
        setJMenuBar(menuBar);
        paintPanel = new JPanel(){
            public void paint(Graphics g) {
                super.paintComponents(g);
                paintPanelAction(g);
            }
        };
        paintPanel.setSize(X + 15, Y + 15); // +15 for the junctions circles will not go out of boundes
        layeredPane = new JLayeredPane();
        layeredPane.add(paintPanel,0);
    }

    /**
     * set buttons panel for the frame
     */
    public void setButtonsPanels(){
        buttonsPanels = new JPanel();
        buttonsPanel1 = new JPanel();
        buttonsPanel2 = new JPanel();
        CRSButton = new JButton(CRS);
        startButton = new JButton("Start");
        stopButton = new JButton("Stop");
        resumeButton = new JButton("Resume");
        infoButton = new JButton("Info");
        buildAMap = new JButton("Build a map"); 
        cloneACar = new JButton("Clone a car");
        Reports = new JButton("Reports");
        CRSButton.addActionListener(this);
        startButton.addActionListener(this);
        stopButton.addActionListener(this);
        resumeButton.addActionListener(this);
        infoButton.addActionListener(this);
        buildAMap.addActionListener(this);
        cloneACar.addActionListener(this);
        Reports.addActionListener(this);
        buttonsPanel1.add(CRSButton);
        buttonsPanel1.add(startButton);
        buttonsPanel1.add(stopButton);
        buttonsPanel1.add(resumeButton);
        buttonsPanel1.add(infoButton);
        buttonsPanel2.add(buildAMap);
        buttonsPanel2.add(cloneACar);
        buttonsPanel2.add(Reports);
        buttonsPanels.setLayout(new GridLayout(2,1));
        buttonsPanel1.setLayout(new GridLayout(1,5));
        buttonsPanel2.setLayout(new GridLayout(1,3));
        buttonsPanels.add(buttonsPanel1);
        buttonsPanels.add(buttonsPanel2);
        add(buttonsPanels,BorderLayout.SOUTH);
    }

    /**
     * set all the actions performes
     */
    public void actionPerformed(ActionEvent e){
        if(e.getSource() instanceof JButton)
            switch(((JButton)e.getSource()).getText()){
                case CRS:
                    new CreateRoadSystemDialog(this);
                    break;
                case "Start":
                    if(driving!=null && driving.getStatIndicator() == 0)
                        driving.start();
                    break;
                    case "Stop":
                    driving.setStatIndicator(2);
                    break;
                case "Resume":
                    driving.setStatIndicator(3);
                    driving.resumeDrivingThread();
                    break;
                case "Info":
                    openTable();
                    break;
                case "Build a map":
                    new BuildAMapDialog(this);
                    break;
                case "Clone a car":
                    CloneACarAction();
                    break;
                case "Reports":
                    showReports();
                    break;
            }
        else if(e.getSource() instanceof JMenuItem){
            switch(((JMenuItem)e.getSource()).getText()){
                case "Help":
                    JOptionPane.showMessageDialog(this,
                    "Home Work 3\nGUI @ Treads");
                    break;
                case "Exit":
                    ExitMenuButtonAction();
                    break;
                case "None":
                    getContentPane().setBackground(null);
                    break;
                case "Magenta": vehicleColor = Color.MAGENTA; changeVehicleColor();
                    break;
                case "Orange": vehicleColor = Color.ORANGE; changeVehicleColor();
                    break;
                case "Random": vehicleColor = null; changeVehicleColor();
                    break;
            }
            if(e.getSource() == blueBackground) getContentPane().setBackground(Color.BLUE); // for blue backGround

            else if(e.getSource() == blueCar) { vehicleColor = Color.BLUE; changeVehicleColor(); } // for blue vehicle
        }
        repaint();
    }

    /**
     * open table with all the Driving object vehicles details
     */
    public void openTable(){
        if(driving!=null){
            if(infoTable == null){
                //headers for the table
                String[] columns = new String[] {"Vehicle", "Type", "Location", "Tim On Loc","Speed"};

                //actual data for the table in a 2d array
                Object[][] data = new Object[driving.getVehicle().size()][5];
                int i=0;	
                for(Vehicle veheicle : driving.getVehicle() )
                    {
                        data[i][0] = i+1;
                        data[i][1] = veheicle.getVehicleType().name();
                        data[i][2] = "Road "+veheicle.getLastRoad().getStartJunction().getJunctionName()+ " - "+veheicle.getLastRoad().getEndJunction().getJunctionName();
                        data[i][3] = veheicle.getTimeOnCurrentPart();
                        data[i][4] = veheicle.getVehicleType().getAverageSpeed();
                        i++;
                    }
                //create table with data
                infoTable = new JTable(data, columns);
                infoTable.setEnabled(false);
                //add the table to the frame
                infoScrollPane = new JScrollPane(infoTable);
                infoScrollPane.setSize(500, 150);
                layeredPane.add(infoScrollPane,0);
                //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                pack();
            }else{infoTable.getParent().remove(infoTable); layeredPane.remove(infoScrollPane); infoTable = null;}
        }
    }

    /**
     * show the repots.txt in the frame
     */
    public void showReports(){
        if(driving != null){
            if(reportsJPanel == null){
                reportsJPanel = Moked.getAllReportsJPanel();
                if(reportsJPanel != null){
                    infoScrollPane = new JScrollPane(reportsJPanel);
                    infoScrollPane.setSize(500, 150);
                    
                    layeredPane.add(infoScrollPane,0);
                    pack();
                }else{
                    JOptionPane.showMessageDialog(
                        this,
                        "No reports yet, try again later",
                        "No reports yet",
                        JOptionPane.PLAIN_MESSAGE,
                        null);
                }
            }else{reportsJPanel.getParent().remove(reportsJPanel); layeredPane.remove(infoScrollPane); reportsJPanel = null;}
        }
    }
    

    /**
     * clone a car action button action, if no car yet a JDialog will open to say that.
     */
    public void CloneACarAction(){
        if(driving!=null){
            Object[] possibilities = new Object[driving.getVehicle().size()];
            for(int i=0; i<driving.getVehicle().size();i++)
                possibilities[i] = i+1;
            Integer choosenVehicleId = (Integer) JOptionPane.showInputDialog(
                        this,
                        "Pleas, choose a car to clone:",
                        "Clone a car Dialog",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        possibilities,
                        1);

            // If a string was returned, clone this vehicle.
            if ((choosenVehicleId != null)) {
                Vehicle newVehicle = VehicleCatch.getVehicle(driving.getVehicle().get(choosenVehicleId));
                driving.getVehicle().add(newVehicle);
                driving.addToAllTimeElements(newVehicle);
                if(driving.getStatIndicator() == 1)
                    newVehicle.start();
                repaint();
                return;
            }
        }else
            JOptionPane.showMessageDialog(this, "Sorry can't clone before creating the map."); 
    }

    /**
     * operate the exit menu button action - by State
     */
    public void ExitMenuButtonAction(){
        Mediator.setState();
        Mediator.getState().exitMenuAction(this);
    }

    /**
     * draw and rotete Vehicles on the road
     * @param g
     * @param x1 - the startJunction of the road X
     * @param y1 - the startJunction of the road Y
     * @param x2 - the endJunction of the road X
     * @param y2 - the endJunction of the road Y
     * @param d - the lenght of the vehicle
     * @param h - half of the vehicle wiגth
     */
    private void drawRotetedVehicle(Graphics g, int x1, int y1, int x2, int y2, int d, int h) {
	    int dx = x2 - x1, dy = y2 - y1, delta = 10;
	    double D = Math.sqrt(dx*dx + dy*dy);
	    double xm = delta, xn = xm, ym = h, yn = -h, x;
	    double xm1 = delta + d, xn1 = xm1, ym1 = h, yn1 = -h, xx; 
	    double sin = dy / D, cos = dx / D;
	    x = xm*cos - ym*sin + x1;
	    xx = xm1*cos - ym1*sin + x1;
	    ym = xm*sin + ym*cos + y1;
	    ym1 = xm1*sin + ym1*cos + y1;
	    xm = x;
	    xm1 = xx; 
	    x = xn*cos - yn*sin + x1;
	    xx = xn1*cos - yn1*sin + x1; 
	    yn = xn*sin + yn*cos + y1;
	    yn1 = xn1*sin + yn1*cos + y1;
	    xn = x;
	    xn1 = xx;
	    int[] xpoints = {(int) xm1, (int) xn1,  (int) xn, (int) xm};
	    int[] ypoints = {(int) ym1, (int) yn1, (int) yn, (int) ym};
	    g.fillPolygon(xpoints, ypoints, 4);
	    g.setColor(Color.BLACK);
	    g.fillOval((int) xm1-2,(int) ym1-2,4,4);
	    g.fillOval((int) xn1-2,(int) yn1-2,4,4);
	    g.fillOval((int) xm-2,(int) ym-2,4,4);
        g.fillOval((int) xn-2,(int) yn-2,4,4);
    }

    /**
     * draw an green arrow on a road
     * @param g
     * @param x1 - the endJunction of the road X
     * @param y1 - the endJunction of the road Y
     * @param x2 - the startJunction of the road X
     * @param y2 - the startJunction of the road Y
     */
    private void drawArrow(Graphics g, int x1, int y1, int x2, int y2) {
        g.setColor(Color.GREEN);
	    double DT = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
        double D = 19; 
        double x;
        double y;
        double T = D / DT;
        x = (1 - T) * x1 + T * x2;
        y = (1 - T) * y1 + T * y2;
        int[] xpoints = {(int) x1, (int) x+2,  (int)x-2};
        int[] ypoints = {(int) y1, (int) y+2, (int)y-2 };
	    g.fillPolygon(xpoints, ypoints, 3);
    }

    /**
     * paint Panel Action of the frame
     * @param g
     */
    public void paintPanelAction(Graphics g){
        if(driving != null){
            for(int i=0;i<driving.getVehicle().size();i++)
            {
                g.setColor(driving.getVehicle().get(i).getVehicleColor());
                drawRotetedVehicle(g,
                (int) driving.getVehicle().get(i).getCurrentX(),
                (int) driving.getVehicle().get(i).getCurrentY(),
                (int) driving.getVehicle().get(i).getLastRoad().getEndJunction().getX(), 
                (int) driving.getVehicle().get(i).getLastRoad().getEndJunction().getY(), 
                Car_Length, Car_Width);
            }
            for(Road road:driving.getMap().getRoads()){
                g.setColor(Color.BLACK);
                g.drawLine((int) road.getStartJunction().getX(),(int) road.getStartJunction().getY(),(int) road.getEndJunction().getX(),(int) road.getEndJunction().getY());
            }
            for(Junction junc:driving.getMap().getJunctions()){ //Draw circles 
                if(junc instanceof LightedJunction)
                    if(((LightedJunction) junc).getLights().isTrafficLightsOn())
                        g.setColor(Color.RED);
                    else g.setColor(Color.GREEN);
                else g.setColor(Color.BLACK);
                g.fillOval((int) junc.getX()-10,(int) junc.getY()-10, 20, 20); 
            }
        
            for(Road road:driving.getMap().getRoads()){
                if(road.isGreenlight() && road.isEnable() && road.getEndJunction() instanceof LightedJunction)
                {
                    if(((LightedJunction) road.getEndJunction()).getLights().isTrafficLightsOn())
                        drawArrow(g,
                        (int) road.getEndJunction().getX(), 
                        (int) road.getEndJunction().getY(), 
                        (int) road.getStartJunction().getX(), 
                        (int) road.getStartJunction().getY());
                }
            }
        }
    } 

    /**
     * change Vehicle Color
     */
    public void changeVehicleColor(){
        Color[] colorNames = new Color[] {Color.BLUE,Color.MAGENTA,Color.ORANGE};
        
        for(Vehicle veh: driving.getVehicle())
        {
           if(vehicleColor == null)
                veh.setVehicleColor(colorNames[(new Random()).nextInt(3)]); 
            else veh.setVehicleColor(vehicleColor);
        }
    }

    /**
     * driving setter
     * @param driving
     */
    public void setDriving(Driving driving) {
        this.driving = driving;
    }

    /**
     * driving getter
     * @return driving
     */
    public Driving getDriving() {
        return driving;
    }
    /**
     * numOfDrivingIteration getter
     * @return numOfDrivingIteration
     */
    public int getNumOfDrivingIteration() {
        return numOfDrivingIteration;
    }
    /**
     * numOfDrivingIteration setter
     * @param numOfDrivingIteration
     */
    public void setNumOfDrivingIteration(int numOfDrivingIteration) {
        this.numOfDrivingIteration = numOfDrivingIteration;
    }
}