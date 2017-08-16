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

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import storemanager.StoreManager;

/**
 *
 * @author Tyler
 */
public class StoreDisplay {
    
    private StoreManager store;
    private JLabel l;
    
    public StoreDisplay(StoreManager store){
        this.store = store;
        this.l = storeName(); 
    }
    
    public JLabel get(){
        return this.l;
    }
    
    private JLabel storeName() {
        JLabel s = new JLabel();
        if (store.getName().equals("Costco")) {
            ImageIcon logo = new ImageIcon("src\\logo_costco.png");
            s.setIcon(logo);
        } else {
            s = new JLabel(store.getName());
        }
        return s;
    }
    
}
