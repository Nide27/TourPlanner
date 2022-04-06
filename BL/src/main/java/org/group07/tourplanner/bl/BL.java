package org.group07.tourplanner.bl;

import org.group07.tourplanner.dal.model.TourItem;
import org.group07.tourplanner.dal.DAL;

import java.util.List;
import java.util.stream.Collectors;

public class BL {
    public List<TourItem> findMatchingTours(String searchText) {
        var tours = DAL.getInstance().tourDao().getAll();
        if (searchText==null || searchText.isEmpty()) {
            return tours;
        }
        return tours.stream()
                .filter(t->t.getName().toLowerCase().contains(searchText.toLowerCase()))
                .collect(Collectors.toList());
    }

    private static BL instance = new BL();

    public static BL getInstance() { return instance; }
}

