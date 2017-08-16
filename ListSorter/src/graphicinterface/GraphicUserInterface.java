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
package graphicinterface;

import java.awt.Graphics;
import javax.swing.JFrame;
import storemanager.StoreManager;

/**
 *
 * @author Tyler
 */
public class GraphicUserInterface extends JFrame {

    private String active;
    private StoreManager store;

    public GraphicUserInterface() {
        this.active = "Print List";
        this.store = new StoreManager();
    }
    
//    IT LOOKS LIKE YOU'RE TRYING TO CREATE A REACTIVE MENU
//    WOULD YOU LIKE SOME HELP WITH THAT?
//    
//    creates window and loads components from varios classse
//    
//    the intent is to have visible and invisible windows whereby the user 
//    can perform various tasks to edit update and manage their list
//    and manage store information
    
    
    public void run(){
        createPanel();
           addPanel();
        
    }
    
    
    private void createPanel() {
        
        
        add(new StoreDisplay(store).get());
        
        if (active.equals("Print List")) {
            
            
        }
        if (active.equals("")) {

        }
        if (active.equals("")) {

        }
        if (active.equals("")) {

        }
        if (active.equals("")) {

        }

    }
    
    private void addPanel(){
        
        
    }
    
    public void setActive(String a){
        this.active = a;
    }

}
