/*
 * *
 * * Copyright (C) 2010-2011 Tyler  Kinnear tkkinnear@gmail.com
 * *
 * * This file is part of {project}.
 * *
 * * {project} can not be copied and/or distributed without the express
 * * permission of {name}
 * *
 */
package main;

import TextInterface.TextMenu;
import graphicinterface.*;

/**
 *
 * @author Tyler
 */
public class Main {

    public static void main(String[] args) {
//        GraphicUserInterface ui = new GraphicUserInterface();
//        gui.run();
//        MainMenu gui = new MainMenu();
        TextMenu tui = new TextMenu();
        tui.run();

    }
}