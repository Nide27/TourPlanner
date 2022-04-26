package org.group07.tourplanner.dal;

import org.group07.tourplanner.dal.model.TourItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TourItemDao implements Dao<TourItem> {
    private List<TourItem> mediaItems = new ArrayList<>();
    private int nextId = 1;

    public TourItemDao(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Optional<TourItem> get(int id) {
        return Optional.ofNullable(mediaItems.get(id));
    }

    @Override
    public List<TourItem> getAll() {
        return mediaItems;
    }

    @Override
    public TourItem create() {
        var tour = new TourItem(nextId, "New Media " + nextId,0.0,"");
        mediaItems.add(tour);
        nextId++;
        return tour;
    }

    @Override
    public void update(TourItem mediaItem, List<?> params) {
        System.out.println(params);
        mediaItem.setName(Objects.requireNonNull(params.get(1), "Name cannot be null").toString());
        mediaItem.setDuration(Double.parseDouble(params.get(2).toString()));
        mediaItem.setContent((params.get(3)==null)?"":params.get(3).toString());
    }

    @Override
    public void delete(TourItem mediaItem) {
        mediaItems.remove(mediaItem);
    }
}
