package ru.whereToEat.model;

import java.io.Serializable;
import java.util.List;

public class Restaurant implements Serializable {

    private Integer restaraunt_Id;

    private String name_restaraunt;

    private int vote_count;

    private List<Meal> menu;

    public Restaurant() {
    }

    public Restaurant(int restaraunt_Id) {
        this.restaraunt_Id = restaraunt_Id;
    }

    public boolean isNew(){
        return restaraunt_Id == null;
    }

    public Integer getRestaraunt_Id() {
        return restaraunt_Id;
    }

    public void setRestaraunt_Id(int restaraunt_Id) {
        this.restaraunt_Id = restaraunt_Id;
    }

    public String getName() {
        return name_restaraunt;
    }

    public void setName(String name_restaraunt) {
        this.name_restaraunt = name_restaraunt;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public List<Meal> getMenu() {
        return menu;
    }

    public void setMenu(List<Meal> menu) {
        this.menu = menu;
    }
}
