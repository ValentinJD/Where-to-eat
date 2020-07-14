package model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "history_votes")
public class History_votes {
    @Id
    private int history_votes_id;

    @Column(name = "user_name")
    @NotBlank
    private String user_name;

    @Column(name = "date_vote")
    @NotNull
    private LocalDateTime date_vote;


    @Column(name = "vote_user")
    @NotNull
    private int vote_user;


    @Embedded
    private Restaurant restaurant;

    public History_votes() {
    }

    public History_votes(int history_votes_id) {
        this.history_votes_id = history_votes_id;
    }
}
