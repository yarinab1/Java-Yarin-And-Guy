package GUI;

import javax.swing.*;

import components.*;

import java.awt.*;
import java.awt.event.*;

/**
 * CreateRoadSystemDialog - a JDialog to choose a castomize amount of Junctions and vehicles for the game
 * @version 1.1 24 Mai 2020
 * @author Guy Cohen 205579808,Yarin Avraham 208401166
 * @see DrivingGameFrame
 */
public class CreateRoadSystemDialog extends JDialog implements ActionListener{
    private JPanel slidersPanel = new JPanel();
    private JPanel butPanel = new JPanel();
    private JSlider juncSlider = new JSlider(JSlider.HORIZONTAL,3, 20, 11);
    private JSlider vehcSlider = new JSlider(JSlider.HORIZONTAL,0, 50, 25);
    private JLabel juncLab = new JLabel("Number of junctions");
    private JLabel vehLab = new JLabel("Number of vehicles");
    private JButton okButton = new JButton("OK");
    private JButton cancelButton = new JButton("Cancel");
    private DrivingGameFrame frame;

    /**
     * condtructor
     * @param frame
     */
    public CreateRoadSystemDialog(DrivingGameFrame frame){
        this.frame = frame;
        juncLab.setHorizontalAlignment(JLabel.HORIZONTAL);
        vehLab.setHorizontalAlignment(JLabel.HORIZONTAL);
        slidersPanel.setLayout(new GridLayout(5,1));
        butPanel.setLayout(new GridLayout(1,2));
        slidersPanel.add(juncLab);
        slidersPanel.add(juncSlider);
        slidersPanel.add(vehLab);
        slidersPanel.add(vehcSlider);

        butPanel.add(okButton);
        butPanel.add(cancelButton);

        juncSlider.setMajorTickSpacing(1);
        juncSlider.setMinorTickSpacing(1);
        juncSlider.setPaintTicks(true);
        juncSlider.setPaintLabels(true);
        vehcSlider.setMajorTickSpacing(5);
        vehcSlider.setMinorTickSpacing(1);
        vehcSlider.setPaintTicks(true);
        vehcSlider.setPaintLabels(true);

        this.add(slidersPanel);
        this.add(butPanel,BorderLayout.SOUTH);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(600,300);
        this.setVisible(true);
        this.setLocation(100, 100);
        
        okButton.addActionListener(this);
        cancelButton.addActionListener(this);
    }

    /**
     * set actions to the buttons in the dialog
     */
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == okButton){
            okAction();
        }else if(e.getSource() == cancelButton){
            dispose();
        }
    }

    /**
     * action to do at ok button click
     */
    public void okAction(){
        if(frame.getDriving() != null && frame.getDriving().getStatIndicator() != 0)
                        frame.getDriving().setStatIndicator(0);
        frame.setDriving(new Driving(juncSlider.getValue(),vehcSlider.getValue(),frame));
        frame.repaint();
        dispose();
    }
}