package org.group07.tourplanner.dal;

import lombok.Getter;
import org.group07.tourplanner.dal.config.DbConfig;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DAL {

    private static DAL instance = null;

    public static DAL getInstance(){
        if(instance == null)
            instance = new DAL();
        return instance;
    }

    private final String dbConfigPath = "./DAL/src/main/resources/org/group07/tourplanner/dal/dbconfig.json";

    private static final Logger logger = LogManager.getLogger(DAL.class);

    private Connection conn;

    @Getter
    private TourItemDao tourItemDao;
    @Getter
    private TourLogDao tourLogDao;

    private DAL() {

        try {
            DbConfig dbConfig = ConfigManager.getInstance().loadConfigFromFile(dbConfigPath, DbConfig.class);

            conn = DriverManager.getConnection(dbConfig.getDbURL(), dbConfig.getDbUser(), dbConfig.getDbPassword());

            tourItemDao = new TourItemDao(conn);
            tourLogDao = new TourLogDao(conn);
        } catch (SQLException e) {
            logger.fatal("Error connecting do DB\n" + e);
        } catch (IOException e) {
            logger.fatal("Error connecting do DB\n" + e);
        }
    }
}
