package org.group07.tourplanner.dal;

import lombok.Getter;
import org.group07.tourplanner.dal.model.TourItem;
import org.group07.tourplanner.dal.model.TourLog;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAL {
    private Dao<TourItem> tourDao;

    private DAL() {
        tourDao = new TourItemDao();
    }
}
