package ru.whereToEat.to;

import ru.whereToEat.model.Meal;

import java.util.List;

public class RestaurantTO {

    private Integer id;

    private String name;

    private int vote_count;

    private List<Meal> menu;

    public RestaurantTO(String name) {
        this.name = name;
    }

    public boolean isNew(){
        return id == null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name_restaraunt) {
        this.name = name_restaraunt;
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

    @Override
    public String toString() {
        return "RestaurantTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", vote_count=" + vote_count +
                ", menu=" + menu +
                '}';
    }
}
