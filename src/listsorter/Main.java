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
package listsorter;

import TextInterface.TextMenu;
import shoppinglist.*;


/**
 *
 * @author Tyler
 */
public class Main {

    public static void main(String[] args) {
        System.out.println(System.getProperty("java.home"));
        TextMenu ui = new TextMenu();
        
        ui.start();
        
    }

}
