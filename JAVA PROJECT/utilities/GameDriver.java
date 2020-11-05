package utilities;

import GUI.*;
//import components.*;

/**
 * GameDriver - a driver to start the GUI for the game.
 */
public class GameDriver {
    public static void main(String[] args) {
        /*  // run whithout GUI - from past virsion (HomeWork2)
        Driving driving=new Driving(10, 20);
        driving.drive(20);
        */
        DrivingGameFrame gameFrame = new DrivingGameFrame(2000); // run GUI
    }
}