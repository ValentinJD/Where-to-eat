DROP TABLE IF EXISTS "meals" CASCADE;
DROP TABLE IF EXISTS "roles" CASCADE;
DROP TABLE IF EXISTS "restaurants" CASCADE;
DROP TABLE IF EXISTS history_votes CASCADE;
DROP TABLE IF EXISTS "users" CASCADE;

CREATE TABLE IF NOT EXISTS "users"
(
    "id"         serial                  NOT NULL,
    "name"       TEXT                    NOT NULL,
    "email"      TEXT                    NOT NULL UNIQUE,
    "password"   char(255)               NOT NULL,
    "enabled"    BOOL      DEFAULT TRUE  NOT NULL,
    "registered" TIMESTAMP DEFAULT now() NOT NULL,
    CONSTRAINT "users_pk" PRIMARY KEY ("id")
) WITH ( OIDS= FALSE );

CREATE TABLE IF NOT EXISTS "restaurants"
(
    "id"         serial NOT NULL,
    "name"       TEXT   NOT NULL,
    "vote_count" int    NOT NULL default 0,
    CONSTRAINT "restaurants_pk" PRIMARY KEY ("id"),
    CONSTRAINT restaurants_idx UNIQUE (name)
) WITH ( OIDS= FALSE );

CREATE TABLE IF NOT EXISTS history_votes
(
    "id"            serial    NOT NULL,
    "user_id"       bigint    NOT NULL,
    "date_vote"     TIMESTAMP NOT NULL,
    "restaurant_id" bigint    NOT NULL,
    CONSTRAINT "history_votes_users" PRIMARY KEY ("id"),
    CONSTRAINT "history_votes_users_fk0" FOREIGN KEY ("user_id")
        REFERENCES "users" ("id") ON DELETE CASCADE,
    CONSTRAINT "history_votes_users_fk1" FOREIGN KEY (restaurant_id)
        REFERENCES "restaurants" ("id") ON DELETE CASCADE
) WITH ( OIDS= FALSE );

CREATE TABLE IF NOT EXISTS "roles"
(
    "user_id" bigint       NOT NULL,
    "role"    varchar(255) NOT NULL,
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    CONSTRAINT "roles_fk0" FOREIGN KEY ("user_id") REFERENCES "users" ("id") ON DELETE CASCADE
) WITH ( OIDS= FALSE );

CREATE TABLE IF NOT EXISTS "meals"
(
    "id"            serial NOT NULL,
    "description"   TEXT   NOT NULL,
    "price"         int    NOT NULL,
    "restaurant_id" bigint NOT NULL,
    CONSTRAINT "meals_pk" PRIMARY KEY ("id"),
    CONSTRAINT "meals_fk0" FOREIGN KEY (restaurant_id) REFERENCES "restaurants" ("id") ON DELETE CASCADE
) WITH ( OIDS= FALSE );