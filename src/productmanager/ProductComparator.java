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
package productmanager;

import java.util.Comparator;
import java.util.Map;

/**
 *
 * @author Tyler
 */
public class ProductComparator implements Comparator<String> {

    Map<String, Product> products;

    public ProductComparator(Map<String, Product> products) {
        this.products = products;

    }

    @Override
    public int compare(String i1, String i2) {

        try {
            int one = products.get(i1).getDepartment().getValue();
            int two = products.get(i2).getDepartment().getValue();
            if (one > two) {
                return 1;
            }
            if (one < two) {
                return -1;
            }
            if (one == two){
                return i1.compareTo(i2);
            }
            return 0;
        } catch (Exception e) {
            System.out.println("*** Comparator unable to sort ***");
            System.out.println(i1 + " vs " + i2);
            System.out.println(e);

            return 0;
        }
    }

}
