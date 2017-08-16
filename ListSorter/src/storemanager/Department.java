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
package storemanager;

/**
 *
 * @author Tyler
 * 
 * I'M DOING THIS WRONG I JUST KNOW IT
 * THIS CLASS IS A LIST OF "DEPARTMENTS" WITHIN A STORE
 * THEY ARE RANKED BASED ON THE ORDER IN WHICH THIS SHOPPER
 * WOULD VISIT THEM.  
 * THIS CLASS IS THE PRIMARY BASIS FOR SHOPPING LIST SORTING
 * 
 * BUT IT'S NOT EVEN STORE DEPENDENT. FULLY STATIC
 */

public enum Department {

    JEWELRY(10),
    ELECTRONICS(20),
    HOUSEWARES(30),
    CLOTHING(35),
    BOOKS(36),
    OUTDOOR(40),
    BAKERY(50),
    BUTCHER(60),
    DELI(70),
    CHEESE(76),
    PREPAREDFOOD(75),
    PRODUCE(80),
    COLDPRODUCE(90),
    PASTA(95),
    BATHROOM(100),
    LAUNDRY(103),
    KITCHEN(105),
    FRIDGE(110),
    DAIRY(115),
    LUNCHMEAT(120),
    FROZEN(130),
    BEVERAGES(138),
    PANTRY(161),
    CEREAL(143),
    CRACKERS(146),
    DRYPASTA(149),
    BAKING(150),
    CONDOMENTS(160),
    SNACKS(170),
    HYGEINE(180),
    PHARMACY(190),
    CHILDCARE(200);

    private final int rating;

    Department(int rating) {
        this.rating = rating;
    }

    public int getValue() {
        return this.rating;
    }

}
