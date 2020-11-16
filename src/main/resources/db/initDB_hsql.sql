DROP TABLE IF EXISTS meals CASCADE;
DROP TABLE IF EXISTS restaurants CASCADE;
DROP TABLE IF EXISTS history_votes CASCADE;
DROP TABLE IF EXISTS count_votes CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq AS INTEGER START WITH 100000;

CREATE TABLE IF NOT EXISTS users
(
    id         INTEGER GENERATED BY DEFAULT AS SEQUENCE global_seq PRIMARY KEY,
    name       varchar(255)            NOT NULL,
    email      VARCHAR(255)            NOT NULL UNIQUE,
    password   VARCHAR(255)            NOT NULL,
    enabled    BOOLEAN   DEFAULT TRUE  NOT NULL,
    registered TIMESTAMP DEFAULT now() NOT NULL,
    role       VARCHAR(255)            NOT NULL
);

CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE IF NOT EXISTS restaurants
(
    id         INTEGER GENERATED BY DEFAULT AS SEQUENCE global_seq,
    name       VARCHAR(255) NOT NULL,
    vote_count int          default 0,
    CONSTRAINT "restaurants_pk" PRIMARY KEY (id),
    CONSTRAINT restaurants_idx UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS history_votes
(
    id            INTEGER GENERATED BY DEFAULT AS SEQUENCE global_seq,
    user_id       bigint  NOT NULL,
    date_vote     TIMESTAMP        DEFAULT now(),
    restaurant_id bigint  NOT NULL,
    vote          integer not null,
    CONSTRAINT history_votes_user_restaurant_idx UNIQUE (user_id, restaurant_id, date_vote),
    CONSTRAINT history_votes_users PRIMARY KEY (id),
    CONSTRAINT history_votes_users_fk0 FOREIGN KEY (user_id)
        REFERENCES users (id) ON DELETE CASCADE,
    CONSTRAINT history_votes_users_fk1 FOREIGN KEY (restaurant_id)
        REFERENCES restaurants (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS meals
(
    id            INTEGER GENERATED BY DEFAULT AS SEQUENCE global_seq,
    description   VARCHAR(1000)   NOT NULL,
    price         float  NOT NULL,
    restaurant_id bigint NOT NULL,
    CONSTRAINT "meals_pk" PRIMARY KEY (id),
    CONSTRAINT "meals_fk0" FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE
);