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
    DELI(6),
    PRODUCE(7),
    COLDPRODUCE(8),
    COLDPRODUCTS(9),
    DAIRY(10),
    PACKAGEDMEATS(11),
    FROZEN(12),
    PANTRY(13),
    SNACKS(14),
    HYGEINE(15),
    PHARMACY(16),
    CHILDCARE(17),
    CLOTHING(18),
    BOOKS(19);
    
    private final int rating;
    Department(int rating){
        this.rating = rating;
    }
    
    public int getValue(){
        return this.rating;
    }
    
}
