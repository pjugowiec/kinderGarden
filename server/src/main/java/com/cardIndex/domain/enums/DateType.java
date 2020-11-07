package com.cardIndex.domain.enums;

public enum DateType {
    Uw("Uw"),
    Um("Um"),
    Ub("Ub"),
    Usz("Usz"),
    Uo("Uo"),
    Ch("Ch"),
    NN("NN"),
    Nup("Nup"),
    Nun("Nun"),
    Del("Del"),
    Inne("Inne"),
    Sunday("Sunday"),
    Saturday("Saturday"),
    Delete("Delete");


    private final String name;

    private DateType(String s){
        name = s;
    }

    public boolean equalsName(String otherName) {
        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }


}
