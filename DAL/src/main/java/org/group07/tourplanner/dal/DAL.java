package org.group07.tourplanner.dal;

import lombok.Getter;
import org.group07.tourplanner.dal.model.TourItem;
import org.group07.tourplanner.dal.model.TourLog;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAL {

    private static DAL instance = null;

    public static DAL getInstance(){
        if(instance == null)
            instance = new DAL();
        return instance;
    }

    private Connection conn;

    @Getter
    private Dao<TourItem> tourItemDao;
    @Getter
    private Dao<TourLog> tourLogDao;

    private DAL() {
        //docker run --rm --detach --name tourplanner -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=admin123 -v "/Users/edinmuhovic/Documents/FH\ 4.Sem/SWE2/TourPlanner":/var/lib/postgresql/data -p 5432:5432 postgres
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/tours", "admin", "admin123");

            tourItemDao = new TourItemDao(conn);
            tourLogDao = new TourLogDao();
        } catch (SQLException e) {
            e.printStackTrace();
            //return;
        }
    }
}
