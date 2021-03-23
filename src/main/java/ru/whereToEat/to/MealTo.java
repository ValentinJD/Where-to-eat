package ru.whereToEat.to;

public class MealTo extends BaseTo {
    private String description;

    private Float price;

    private Integer restaurantId;


    public MealTo(Integer id, String description, Float price, Integer restaurantId) {
        super(id);
        this.description = description;
        this.price = price;
        this.restaurantId = restaurantId;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
