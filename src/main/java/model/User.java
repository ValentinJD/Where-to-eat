package model;

import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;


@Entity
public class User {

    @Id
    private Integer userId;

    private String email;

    private String password;

    private boolean enabled = true;

    private Date registered = new Date();

//    @Column(name = "roles")
    @ElementCollection(fetch = FetchType.EAGER)
    @BatchSize(size = 200)
    private Set<Role> roles;

    private boolean vote_on_day_now;

    public User() {
    }

    public boolean isNew() {
        return userId == null;
    }
}