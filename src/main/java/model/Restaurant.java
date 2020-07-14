package model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Embeddable
@Table(name = "restaraunt")
public class Restaurant implements Serializable {

    @Id
    private int restaraunt_Id;

    @Column(name = "name_restaraunt")
    @NotBlank
    private String name_restaraunt;

    @Column(name = "vote_count")
    @NotNull
    private int vote_count;

    @Column(name = "menu")
    @NotBlank
    private String menu;

    public Restaurant() {
    }

    public Restaurant(int restaraunt_Id) {
        this.restaraunt_Id = restaraunt_Id;
    }
}
