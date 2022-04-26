CREATE DATABASE

docker exec -it tourplanner bash

CREATE TABLE items (
                       id serial PRIMARY KEY,
                       name VARCHAR ( 50 ) NOT NULL,
                       description VARCHAR ( 50 ) NOT NULL,
                       departure VARCHAR ( 50 ) NOT NULL,
                       destination VARCHAR ( 50 ) NOT NULL,
                       transport VARCHAR ( 20 ) NOT NULL,
                       distance FLOAT NOT NULL,
                       estimate FLOAT NOT NULL
);


CREATE TABLE logs (
                      tourid INT NOT NULL,
                      datetime timestamp NOT NULL,
                      comment VARCHAR ( 255 ) NOT NULL,
                      difficulty INT NOT NULL,
                      duration FLOAT NOT NULL,
                      rating INT NOT NULL,
                      PRIMARY KEY (tourid, datetime),
                      CONSTRAINT fk_tour
                          FOREIGN KEY(tourid)
                              REFERENCES items(id)
                              ON DELETE CASCADE
                              ON UPDATE CASCADE
);