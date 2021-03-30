DROP TABLE IF EXISTS game_state;
DROP TABLE IF EXISTS player;


CREATE TABLE game_state (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    player_name VARCHAR(16) NOT NULL,
    current_map text NOT NULL,
    saved_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    player_id integer NOT NULL
);


CREATE TABLE player (
    id serial NOT NULL PRIMARY KEY,
    hp integer NOT NULL,
    shield BOOLEAN,
    friends INT,
    weapons INT,
    box_key INT
);

ALTER TABLE ONLY game_state
    ADD CONSTRAINT fk_player_id FOREIGN KEY (player_id) REFERENCES player(id) ON DELETE CASCADE;