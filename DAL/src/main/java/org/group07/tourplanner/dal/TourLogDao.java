package org.group07.tourplanner.dal;

import org.group07.tourplanner.dal.model.TourLog;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class TourLogDao implements Dao<TourLog> {
    private List<TourLog> tourLogs = new ArrayList<>();
    private int nextId = 1;

    public TourLogDao(){
        tourLogs.add(new TourLog(nextId++, 100, "comment1", "diff1", 1000, 1));
        tourLogs.add(new TourLog(nextId++, 10000, "comment2", "diff2", 1001, 3));
        tourLogs.add(new TourLog(nextId++, 1000000, "comment3", "diff3", 1002, 5));
    }

    @Override
    public Optional<TourLog> get(int id) { return Optional.ofNullable(tourLogs.get(id)); }

    @Override
    public List<TourLog> getAll() { return new ArrayList<>(); }

    @Override
    public void create(TourLog tourLog) {
        var log = new TourLog(nextId, 0, "New Log " + nextId, "", 0, 0);
        tourLogs.add(log);
        nextId++;
        return;
    }

    @Override
    public void update(TourLog tourLog) {

    }

    @Override
    public void delete(TourLog tourLog) {
        tourLogs.remove(tourLog);
    }

}
