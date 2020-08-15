package model;

import java.time.LocalDateTime;

public class History_votes {

    private int history_votes_id;


    private String user_name;


    private LocalDateTime date_vote;



    private int vote_user;



    private Restaurant restaurant;

    public History_votes() {
    }

    public History_votes(int history_votes_id) {
        this.history_votes_id = history_votes_id;
    }
}
