ALTER TABLE "history_votes_users" DROP CONSTRAINT IF EXISTS "history_votes_users_fk0";

ALTER TABLE "history_votes_users" DROP CONSTRAINT IF EXISTS "history_votes_users_fk1";

ALTER TABLE "roles" DROP CONSTRAINT IF EXISTS "roles_fk0";

ALTER TABLE "meals" DROP CONSTRAINT IF EXISTS "meals_fk0";

DROP TABLE IF EXISTS "users";

DROP TABLE IF EXISTS "restaraunts";

DROP TABLE IF EXISTS "history_votes_users";

DROP TABLE IF EXISTS "roles";

DROP TABLE IF EXISTS "meals";


CREATE TABLE "users"
(
    "id"         serial    NOT NULL,
    "email"      TEXT      NOT NULL UNIQUE,
    "password"   char(255) NOT NULL,
    "enabled"    bool      NOT NULL DEFAULT 'false',
    "registered" TIMESTAMP NOT NULL,
    CONSTRAINT "users_pk" PRIMARY KEY ("id")
) WITH (
      OIDS= FALSE
    );



CREATE TABLE "restaraunts"
(
    "id"              serial NOT NULL,
    "name_restaraunt" TEXT   NOT NULL,
    "vote_count"      serial NOT NULL,
    "menu"            TEXT   NOT NULL,
    CONSTRAINT "restaraunts_pk" PRIMARY KEY ("id")
) WITH (
      OIDS= FALSE
    );



CREATE TABLE "history_votes_users"
(
    "id"            serial    NOT NULL,
    "user_id"       bigint    NOT NULL,
    "date_vote"     TIMESTAMP NOT NULL,
    "restaraunt_id" bigint    NOT NULL,
    CONSTRAINT "history_votes_users_pk" PRIMARY KEY ("id")
) WITH (
      OIDS= FALSE
    );
ALTER TABLE "history_votes_users"
    ADD CONSTRAINT "history_votes_users_fk0" FOREIGN KEY ("user_id") REFERENCES "users" ("id");

ALTER TABLE "history_votes_users"
    ADD CONSTRAINT "history_votes_users_fk1" FOREIGN KEY ("restaraunt_id") REFERENCES "restaraunts" ("id");



CREATE TABLE "roles"
(
    "user_id" bigint       NOT NULL,
    "role"    varchar(255) NOT NULL
) WITH (
      OIDS= FALSE
    );
ALTER TABLE "roles"
    ADD CONSTRAINT "roles_fk0" FOREIGN KEY ("user_id") REFERENCES "users" ("id");



CREATE TABLE "meals"
(
    "id"            serial NOT NULL,
    "description"   TEXT   NOT NULL,
    "price"         float4 NOT NULL,
    "restaraunt_id" bigint NOT NULL,
    CONSTRAINT "meals_pk" PRIMARY KEY ("id")
) WITH (
      OIDS= FALSE
    );

ALTER TABLE "meals"
    ADD CONSTRAINT "meals_fk0" FOREIGN KEY ("restaraunt_id") REFERENCES "restaraunts" ("id");





