package ru.whereToEat.model;

import org.springframework.util.Assert;

import javax.persistence.*;

import static ru.whereToEat.model.AbstractBaseEntity.START_SEQ;

@Entity
@Table(name = "meals")
@Access(AccessType.FIELD)
@NamedQueries({
        @NamedQuery(name = Meal.ALL_SORTED, query = "SELECT m FROM Meal m ORDER BY m.id"),
        @NamedQuery(name = Meal.ALL_SORTED_BY_RESTAURANT_ID, query = "SELECT m FROM Meal m WHERE m.restaurant.id=?1 order by m.id")
})
public class Meal  {
    public static final String ALL_SORTED = "Meal.AllSorted";
    public static final String ALL_SORTED_BY_RESTAURANT_ID = "Meal.AllSortedByRestaurantId";

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    private Integer id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private Float price;

    @ManyToOne(fetch = FetchType.LAZY)
    private Restaurant restaurant;

    public Meal() {
    }

    public Meal(String description, Float price) {
        this(null, description, price);
    }

    public Meal(Integer id, String description, Float price) {
        this.id = id;
        this.description = description;
        this.price = price;
    }

    public Meal(String description, Float price, Restaurant restaurant) {
        this.description = description;
        this.price = price;
        this.restaurant = restaurant;
    }

    public Meal(Integer id, String description, Float price, Restaurant restaurant) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.restaurant = restaurant;
    }

    // doesn't work for hibernate lazy proxy
    public int id() {
        Assert.notNull(id, "Entity must has id");
        return id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public boolean isNew() {
        return id == null;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", restaurant=" + restaurant +
                '}';
    }
}
