package components;

import GUI.DrivingGameFrame;

/**
 * CanExitState - state that aplly exit from menu exitButtom in DrivingGameFrame object
 * @version 1.4 7 June 2020
 * @author Guy Cohen 205579808,Yarin Avraham 208401166
 * @see Mediator
 * @see DrivingGameFrame
 */
public class CanExitState implements State {

   @Override
    public void exitMenuAction(DrivingGameFrame frame){
       System.exit(0);
    }
 
    @Override
    public String toString(){
       return "Can Exit State";
    }
 }