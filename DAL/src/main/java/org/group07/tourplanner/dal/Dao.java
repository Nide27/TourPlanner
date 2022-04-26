package org.group07.tourplanner.dal;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    Optional<T> get(int id);

    List<T> getAll();

    void create(T t);

    void update(T t);

    void delete(T t);
}
