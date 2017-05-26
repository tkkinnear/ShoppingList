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
package UserInterface;

import java.util.Scanner;
import productmanager.Department;
import productmanager.ProductManager;

/**
 *
 * @author Tyler
 */
public class GetCommand {

    private Scanner reader;

    public GetCommand() {
        this.reader = new Scanner(System.in);

    }

    public String next() {
        return reader.nextLine().toLowerCase();
    }

    public Department department() {
        while (true) {
            try {
                System.out.print("Enter the Department for this item: ");
                String d = next().toUpperCase();
                Department dept = Department.valueOf(d);
                return dept;

            } catch (Exception e) {
                System.out.println("not a valid Department.");
            }
        }
    }

    public double price() {
        while (true) {
            try {
                System.out.print("Enter the price for this item: ");
                double d = Double.parseDouble(next());
                return d;
            } catch (Exception e) {
                System.out.println("invalid entry. \"0\" to give up");
            }
        }
    }
    
    public String name(ProductManager store){
            System.out.println("Enter a name for this product: ");
        String n = next();
        while (true) {
            if (store.getProduct(n) != null) {
                System.out.println("Found listing for " + n);
                System.out.println(store.getProduct(n).toString());
            } else {
                System.out.println("No listing found for " + n);
            }
            System.out.print("Enter new name, or hit return to keep: ");
            String command = next();
            if (command.equals("")) {
                return n;
            }
        }
    }

}
