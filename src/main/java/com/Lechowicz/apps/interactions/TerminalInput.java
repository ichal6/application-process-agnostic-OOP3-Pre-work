package com.Lechowicz.apps.interactions;

import java.util.Scanner;

public class TerminalInput extends InputManager {
    private View view;
    public TerminalInput(){
        view = new TerminalView();
    }
    @Override
    public Integer getIntFromUser() {
        Integer input = 0;
        Scanner scannerFromUser = new Scanner(System.in);

        while(!scannerFromUser.hasNextInt()){
            view.print("Wrong input! Please insert the integer number");
            scannerFromUser.next();
        }
        input = scannerFromUser.nextInt();

        return input;
    }

    @Override
    public String getStringFromUser() {
        Scanner scannerFromUser = new Scanner(System.in);
        return scannerFromUser.nextLine();
    }
}
