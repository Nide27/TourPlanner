package org.group07.tourplanner.dal;

import org.group07.tourplanner.dal.config.DbConfig;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ConfigManagerTest {

    @Test
    void getInstance() {
        Object obj = ConfigManager.getInstance();

        assertTrue(obj instanceof ConfigManager);
    }

    @Test
    void loadConfigFromFile() throws IOException {

        DbConfig cnf = ConfigManager.getInstance().loadConfigFromFile("./src/test/resources/org/group07/tourplanner/dal/testconfig.json", DbConfig.class);

        assertEquals("test_url", cnf.getDbURL());
        assertEquals("test_user", cnf.getDbUser());
        assertEquals("test_password", cnf.getDbPassword());
    }
}