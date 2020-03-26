package com.Lechowicz.apps.application;

import com.Lechowicz.apps.interactions.*;

public class Controller {
    View view = new TerminalView();
    InputManager input = new TerminalInput();

    private void answerOne(){
        view.print("Write a query that returns the 2 name columns of the mentors table\n");

    }
}
