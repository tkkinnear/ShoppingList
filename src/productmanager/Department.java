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

/**
 *
 * @author Tyler
 */
public enum Department {
    
    JEWELRY(1),
    ELECTRONICS(2),
    HOUSEWARES(3),
    OUTDOOR(4),
    BAKERY(5),
    BUTCHER(6),
    DELI(7),
    PRODUCE(8),
    COLDPRODUCE(9),
    DAIRY(10),
    LUNCHMEAT(11),
    FROZEN(12),
    PANTRY(13),
    SNACKS(14),
    BAKING(15),
    CONDOMENTS(16),
    HYGEINE(17),
    PHARMACY(18),
    CHILDCARE(19),
    CLOTHING(20),
    BOOKS(21);
    
    private final int rating;
    Department(int rating){
        this.rating = rating;
    }
    
    public int getValue(){
        return this.rating;
    }
    
}
