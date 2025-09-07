package org.randomlima.recipeone;

public enum Gear {
    REVERSE(-3),
    FIRST(3),
    SECOND(2.2),
    THIRD(1.8),
    FOURTH(1.5),
    SIXTH(1.1),
    SEVENTH(0.9),
    EIGHTH(0.8);



    private final double ratio;
    Gear(double ratio){
        this.ratio = ratio;
    }
    public double getRatio(){
        return ratio;
    }
}
