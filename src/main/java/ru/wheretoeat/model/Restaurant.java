package ru.wheretoeat.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.util.Assert;
import ru.wheretoeat.View;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

import static org.hibernate.validator.constraints.SafeHtml.WhiteListType.NONE;
import static ru.wheretoeat.model.AbstractBaseEntity.START_SEQ;

@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "restaurants")
@Access(AccessType.FIELD)
@NamedQueries(
        @NamedQuery(name = Restaurant.ALL_SORTED, query = "SELECT r FROM Restaurant r order by r.id")
)
public class Restaurant implements Serializable {

    public static final String ALL_SORTED = "Restaurant.AllSorted";
    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    private Integer id;

    @Column(name = "name", nullable = false)
    @NotBlank
    @Size(min = 2, max = 1000)
    @SafeHtml(whitelistType = NONE)
    private String name;

    @Column(name = "vote_count", nullable = false)

    private int vote_count;

    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @BatchSize(size = 200)
    @OrderBy("id ASC ")
    private List<Meal> menu;

    public Restaurant() {
    }

    public Restaurant(String name) {
        this.name = name;
    }

    public Restaurant(Integer id, String name, int vote_count) {
        this.id = id;
        this.name = name;
        this.vote_count = vote_count;
    }

    // doesn't work for hibernate lazy proxy
    public int id() {
        Assert.notNull(id, "Entity must has id");
        return id;
    }

    public boolean isNew() {
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
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", vote_count=" + vote_count +
                ", menu=" + "menu lazy Test" +
                '}';
    }

    public boolean getIsNew() {
        return isNew();
    }
}
