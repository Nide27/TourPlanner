package org.group07.tourplanner.dal;

import lombok.Getter;
import org.group07.tourplanner.dal.config.DbConfig;
import org.group07.tourplanner.dal.model.TourItem;

import java.io.IOException;
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

    private final String dbConfigPath = "/Users/edinmuhovic/Documents/FH 4.Sem/SWE2/TourPlanner/DAL/src/main/resources/org/group07/tourplanner/dal/dbconfig.json";

    private Connection conn;

    @Getter
    private Dao<TourItem> tourItemDao;
    @Getter
    private TourLogDao tourLogDao;

    private DAL() {
        //docker run --rm --detach --name tourplanner -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=admin123 -v "/Users/edinmuhovic/Documents/FH\ 4.Sem/SWE2/TourPlanner":/var/lib/postgresql/data -p 5432:5432 postgres
        try {
            ConfigManager configManager = ConfigManager.getInstance();

            DbConfig dbConfig = ConfigManager.getInstance().loadConfigFromFile(dbConfigPath, DbConfig.class);

            conn = DriverManager.getConnection(dbConfig.getDbURL(), dbConfig.getDbUser(), dbConfig.getDbPassword());

            tourItemDao = new TourItemDao(conn);
            tourLogDao = new TourLogDao(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            //return;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
