CREATE DATABASE

docker run --rm --detach --name tourplanner -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=admin123 -v "/Users/edinmuhovic/Documents/FH\ 4.Sem/SWE2/TourPlanner":/var/lib/postgresql/data -p 5432:5432 postgres

docker exec -it tourplanner bash

psql -p 5432 -h localhost -U admin

INSERT INTO items (name, description, departure, destination, transport, distance, estimate) VALUES ('Erste geile Tour', 'War wirklich MEEEGA!', 'Korneuburg', 'Wien', 'Bike', 40, 120);
INSERT INTO logs (tourid, datetime, comment, difficulty, duration, rating) VALUES (3, '20120618 10:34:09 AM', 'Schwer war es aber habe es natürlich geschafft, bin einfacher der geilste Typ', 3, 120, 5);

CREATE TABLE items (
                       id serial PRIMARY KEY,
                       name VARCHAR ( 50 ) NOT NULL,
                       description VARCHAR ( 50 ) NOT NULL,
                       departure VARCHAR ( 50 ) NOT NULL,
                       destination VARCHAR ( 50 ) NOT NULL,
                       transport VARCHAR ( 20 ) NOT NULL,
                       distance double precision NOT NULL,
                       estimate varchar NOT NULL
);


CREATE TABLE logs (
                      tourid INT NOT NULL,
                      datetime DATE NOT NULL,
                      comment VARCHAR ( 255 ) NOT NULL,
                      difficulty INT NOT NULL,
                      duration INT NOT NULL,
                      rating INT NOT NULL,
                      PRIMARY KEY (tourid, datetime),
                      CONSTRAINT fk_tour
                          FOREIGN KEY(tourid)
                              REFERENCES items(id)
                              ON DELETE CASCADE
                              ON UPDATE CASCADE
);