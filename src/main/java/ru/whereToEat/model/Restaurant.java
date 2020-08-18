package ru.whereToEat.model;

import java.io.Serializable;

public class Restaurant implements Serializable {


    private int restaraunt_Id;

    private String name_restaraunt;


    private int vote_count;


    private String menu;

    public Restaurant() {
    }

    public Restaurant(int restaraunt_Id) {
        this.restaraunt_Id = restaraunt_Id;
    }
}
