package org.group07.tourplanner.dal;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    Optional<T> get(int id);

    List<T> getAll();

    T create();

    void update(T t, List<?> params);

    void delete(T t);
}
