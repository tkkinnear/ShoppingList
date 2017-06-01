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

import TextInterface.TextCommand;
import filemanager.FileManager;
import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;
import productmanager.StoreManager;
import shoppinglist.ShoppingList;

/**
 *
 * @author Tyler
 */
public class GraphicInterface implements Runnable {

    private final String name;
    private final ShoppingList shoppingList;
    private StoreManager store;
    private final FileManager saveFile;
    private final Scanner reader;
    private final TextCommand c;
    private JFrame frame;

    public GraphicInterface() {
        this.name = "Tyler";
        this.shoppingList = new ShoppingList();
        this.store = new StoreManager();
        this.saveFile = new FileManager("Shopping List", "src\\testOutput.txt");
        this.reader = new Scanner(System.in);
        this.c = new TextCommand();

    }

    @Override
    public void run() {
        JFrame frame = new JFrame("Shopping List");
        frame.setPreferredSize(new Dimension(500, 400));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mainMenu(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void storeName(Container container) {

    }

    private void mainMenu(Container container) {
        container.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        container.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        JButton button;
        JLabel s = new JLabel();
        if (store.getName().equals("Costco")) {
            ImageIcon logo = new ImageIcon("src\\logo_costco.png");
            s.setIcon(logo);
        } else {
            s = new JLabel(store.getName());
        }
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.PAGE_START;
        c.gridwidth = 3;
        c.gridheight = 2;
        c.weightx = 0.5;
        c.ipady = 20;
        c.gridx = 0;
        c.gridy = 0;
        container.add(s, c);

        button = new JButton("Change Store");
        c.weightx = 0.3;
        c.gridwidth = 1;
        c.gridheight = 1;

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        container.add(button, c);

        button = new JButton("Store Audit");
        c.weightx = 0.5;
        //c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        container.add(button, c);

        button = new JButton("Add to your list");
        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
        container.add(button, c);

        button = new JButton("Print List");
        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 5;
        container.add(button, c);
        
        JTextArea t = new JTextArea(shoppingList.getCost(store));
        for (String each : shoppingList.printList(store)) {
            t.append(each + "\n");
        }
        t.setMinimumSize(new Dimension(200,200));
        t.getAutoscrolls();
        c.weightx = 0.5;
        c.gridwidth = 1;
        c.gridheight = 4;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.EAST;
        c.insets = new Insets(0, 20, 0, 20);
        c.gridx = 1;
        c.gridy = 2;
        container.add(t, c);

        button = new JButton("Save");
        c.weightx = 0.5;
        c.insets = new Insets(20, 0, 0, 20);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.PAGE_END;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.ipady = 0;
        c.gridx = 0;
        c.gridy = 10;
        container.add(button, c);

        button = new JButton("Exit");
        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(20, 30, 0, 0);
        c.gridx = 1;
        c.gridy = 10;
        container.add(button, c);

    }

    private void menuOptions(Container container) {

    }

    public JFrame getFrame() {
        return frame;
    }
}
