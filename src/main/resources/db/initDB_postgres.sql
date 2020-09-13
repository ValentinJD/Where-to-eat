DROP TABLE IF EXISTS "meals" CASCADE;
DROP TABLE IF EXISTS "roles" CASCADE;
DROP TABLE IF EXISTS "restaurants" CASCADE;
DROP TABLE IF EXISTS history_votes CASCADE;
DROP TABLE IF EXISTS "users" CASCADE;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE IF NOT EXISTS "users"
(
    id           INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    "name"       TEXT                              NOT NULL,
    "email"      TEXT                              NOT NULL UNIQUE,
    "password"   char(255)                         NOT NULL,
    "enabled"    BOOL                DEFAULT TRUE  NOT NULL,
    "registered" TIMESTAMP           DEFAULT now() NOT NULL
) WITH ( OIDS= FALSE );

CREATE TABLE IF NOT EXISTS "restaurants"
(
    "id"         INTEGER DEFAULT nextval('global_seq'),
    "name"       TEXT NOT NULL,
    "vote_count" int  NOT NULL       default 0,
    CONSTRAINT "restaurants_pk" PRIMARY KEY ("id"),
    CONSTRAINT restaurants_idx UNIQUE (name)
) WITH ( OIDS= FALSE );

CREATE TABLE IF NOT EXISTS history_votes
(
    "id"            INTEGER DEFAULT nextval('global_seq'),
    "user_id"       bigint    NOT NULL,
    "date_vote"     TIMESTAMP DEFAULT now() NOT NULL,
    "restaurant_id" bigint    NOT NULL,
    "vote" integer not null default 0,
    CONSTRAINT history_votes_user_restaurant_idx UNIQUE (user_id, restaurant_id),
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
    "id"            INTEGER DEFAULT nextval('global_seq'),
    "description"   TEXT   NOT NULL,
    "price"         float    NOT NULL,
    "restaurant_id" bigint NOT NULL,
    CONSTRAINT "meals_pk" PRIMARY KEY ("id"),
    CONSTRAINT "meals_fk0" FOREIGN KEY (restaurant_id) REFERENCES "restaurants" ("id") ON DELETE CASCADE
) WITH ( OIDS= FALSE );