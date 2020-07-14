ALTER TABLE "history_votes"

DROP CONSTRAINT IF EXISTS "history_votes_fk0";

DROP TABLE IF EXISTS "user";

DROP TABLE IF EXISTS "restaraunt";

DROP TABLE IF EXISTS "history_votes";

DROP TABLE IF EXISTS "roles";

CREATE TABLE "user"
(
    "user_id"         serial    NOT NULL,
    "name"            TEXT      NOT NULL,
    "email"           TEXT      NOT NULL UNIQUE,
    "password"        char(255) NOT NULL,
    "enabled"         bool      NOT NULL DEFAULT 'false',
    "registered"      TIMESTAMP NOT NULL,
    "vote_on_day_now" BOOLEAN   NOT NULL DEFAULT 'false',
    CONSTRAINT "user_pk" PRIMARY KEY ("user_id")
) WITH (
  OIDS=FALSE
);

CREATE TABLE "restaraunt"
(
    "restaraunt_id"   serial NOT NULL,
    "name_restaraunt" TEXT   NOT NULL,
    "vote_count"      serial NOT NULL,
    "menu"            TEXT   NOT NULL,
    CONSTRAINT "restaraunt_pk" PRIMARY KEY ("restaraunt_id")
) WITH (
  OIDS=FALSE
);

CREATE TABLE "history_votes"
(
    "history_votes_id" serial    NOT NULL,
    "user_name"        TEXT      NOT NULL,
    "date_vote"        TIMESTAMP NOT NULL,
    "history_fk"       int       NOT NULL,
    "vote_user"        int       NOT NULL DEFAULT '0',
    CONSTRAINT "history_votes_pk" PRIMARY KEY ("history_votes_id")
) WITH (
  OIDS=FALSE
);

CREATE TABLE "roles"
(
    "roles_id" bigint       NOT NULL,
    "role"     varchar(255) NOT NULL
) WITH ( OIDS=FALSE );

ALTER TABLE "history_votes"
    ADD CONSTRAINT "history_votes_fk0" FOREIGN KEY ("history_fk") REFERENCES "user" ("user_id");

