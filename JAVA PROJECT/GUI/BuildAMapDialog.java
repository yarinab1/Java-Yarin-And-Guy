package GUI;

import javax.swing.*;
import components.*;
import java.awt.*;
import java.awt.event.*;

/**
 * BuildAMapDialog - a JDialog to choose what type of map to create (City / country) 
 * @version 1.4 7 June 2020
 * @author Guy Cohen 205579808,Yarin Avraham 208401166
 * @see Mediator
 * @see DrivingGameFrame
 */
public class BuildAMapDialog extends JDialog implements ActionListener{
    private JPanel butPanel = new JPanel();
    private JLabel titleLab = new JLabel("Pleas, choose a map to build !");
    private JButton cityButton = new JButton("City");
    private JButton countryButton = new JButton("Country");
    private DrivingGameFrame frame;

    /**
     * condtructor
     * @param frame
     */
    public BuildAMapDialog(DrivingGameFrame frame){
        this.frame = frame;
        titleLab.setHorizontalAlignment(JLabel.HORIZONTAL);
        
        butPanel.setLayout(new GridLayout(1,2));
        
        butPanel.add(cityButton);
        butPanel.add(countryButton);

        this.add(titleLab);
        this.add(butPanel,BorderLayout.SOUTH);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setSize(400, 200);
        this.setLocation(200, 200);
        cityButton.addActionListener(this);
        countryButton.addActionListener(this);
    }

    /**
     * set actions to the buttons in the dialog
     */
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == cityButton){
            cityButtonAction();
        }else if(e.getSource() == countryButton){
            countryButtonAction();
        }
    }

    /**
     * action to do at city button click
     */
    public void cityButtonAction(){
        creator(new CityBuilder());
    }

    /**
     * action to do at country button click
     */
    public void countryButtonAction(){
        creator(new CountryBuilder());
    }

    /**
     * create a map by MapBuilder object
     * @param mapBuilder
     */
    public void creator(MapBuilder mapBuilder){
        if(frame.getDriving() != null && frame.getDriving().getStatIndicator() != 0)
            frame.getDriving().setStatIndicator(0);
        MapCreator mapCreator = new MapCreator(mapBuilder);
        mapCreator.constructMap();
        frame.setDriving(new Driving(mapCreator.getMap() ,frame));
        frame.repaint();
        dispose();
    }
}