package components;

import javax.swing.JOptionPane;
import GUI.DrivingGameFrame;

/**
 * CanNotExitState - state that aplly exit from menu exitButtom in DrivingGameFrame object
 * @version 1.4 7 June 2020
 * @author Guy Cohen 205579808,Yarin Avraham 208401166
 * @see Mediator
 * @see DrivingGameFrame
 */

public class CanNotExitState implements State {
   @Override
   public void exitMenuAction(DrivingGameFrame frame){
      JOptionPane.showMessageDialog(
                     frame,
                     "Sorry, not all drivers read their reports",
                     "Can't exit yet...",
                     JOptionPane.PLAIN_MESSAGE,
                     null);
   };

   @Override
   public String toString(){
      return "Can Exit State";
   }
 }