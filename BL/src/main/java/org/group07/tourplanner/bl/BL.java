package org.group07.tourplanner.bl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.group07.tourplanner.dal.model.TourItem;
import org.group07.tourplanner.dal.DAL;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class BL {

    private static final Logger logger = LogManager.getLogger(DAL.class);

    public List<TourItem> findMatchingTours(String searchText) throws SQLException {
        var tours = DAL.getInstance().getTourItemDao().getAll();
        if (searchText == null || searchText.isEmpty()) {
            return tours;
        }

        return tours.stream()
                .filter(t -> {
                    try {
                        return t.getName().toLowerCase().contains(searchText.toLowerCase()) ||
                                t.getDescription().toLowerCase().contains(searchText.toLowerCase()) ||
                                DAL.getInstance().getTourLogDao().getAllById(t.getId()).stream()
                                .filter(s -> s.getComment().toLowerCase().contains(searchText.toLowerCase())).collect(Collectors.toList()).size() > 0;
                    } catch (SQLException e) {
                        logger.warn("Could not search in TourLogs:\n" + e);
                        return false;
                    }
                })
                .collect(Collectors.toList());
    }

    private static BL instance = new BL();

    public static BL getInstance() {
        return instance;
    }
}

