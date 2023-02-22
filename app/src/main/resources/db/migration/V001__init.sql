CREATE TABLE distribution (
                              id BIGSERIAL UNIQUE PRIMARY KEY,
                              name VARCHAR (255),
                              min_players Integer NOT NULL,
                              max_players Integer NOT NULL
);
