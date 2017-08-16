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

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import storemanager.StoreManager;
import storemanager.ShoppingList;

/**
 *
 * @author Tyler
 */
public class MainMenu implements Runnable {

    private final String name;
    private final ShoppingList shoppingList;
    private StoreManager store;
    
   
    private JFrame frame;
    private String active;

    public MainMenu() {
        this.name = "Tyler";
        this.shoppingList = new ShoppingList();
        this.store = new StoreManager();
    }

    @Override
    public void run() {
        JFrame frame = new JFrame("Shopping List");
        frame.setPreferredSize(new Dimension(500, 415));
        frame.setResizable(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainMenu(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
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

    private void mainMenu(Container container) {
        container.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        container.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        JButton button;
        JLabel s = storeName();
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;
        c.gridwidth = 3;
        c.gridheight = 2;
        c.weightx = 0.5;
        c.ipady = 20;
        c.gridx = 0;
        c.gridy = 0;
        container.add(s, c);

        button = new JButton("Change Store");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.3;
        c.gridheight = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.gridx = 0;
        c.gridy = 2;
        button.addActionListener(new MenuListener());
        container.add(button, c);

        button = new JButton("Add to your list");
        c.gridx = 0;
        c.gridy = 3;
        button.addActionListener(new MenuListener());
        container.add(button, c);

        button = new JButton("Print List");
        c.gridx = 0;
        c.gridy = 4;
        button.addActionListener(new MenuListener());
        container.add(button, c);
        
        button = new JButton("Store Manager");
        c.gridx = 0;
        c.gridy = 5;
        button.addActionListener(new MenuListener());
        container.add(button, c);

        button = new JButton("Save");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.PAGE_END;
        c.weighty = 1.0;
        c.gridx = 0;
        c.gridy = 10;
//        button.addActionListener(new MenuListener());
        container.add(button, c);
        
        //** start shopping list container **
        c.anchor = GridBagConstraints.EAST;
        c.fill = GridBagConstraints.BOTH;
        c.ipady = 0;
        c.weightx = 1.5;
        c.gridheight = 10;
        c.insets = new Insets(0, 20, 0, 0);
        c.gridx = 2;
        c.gridy = 2;
        container.add(shoppingList(), c);

    }

    private void menuOptions(Container container) {

    }

    public JFrame getFrame() {
        return frame;
    }

    public JScrollPane shoppingList() {
        JTextArea t = new JTextArea();
        for (String each : shoppingList.printList(store)) {
            t.append(each + "\n");
        }
        t.setEnabled(false);
        t.setDisabledTextColor(Color.BLACK);
        t.setFont(new Font("Roman", Font.PLAIN, 14));
        JScrollPane scroll = new JScrollPane(t);
        scroll.setMinimumSize(new Dimension(200, 275));
        return scroll;
    }
}
