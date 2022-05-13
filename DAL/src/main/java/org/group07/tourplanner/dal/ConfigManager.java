package org.group07.tourplanner.dal;

import lombok.Getter;
import org.group07.tourplanner.dal.config.DbConfig;

import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;

public class ConfigManager {

    private static ConfigManager instance;

    @Getter
    private String dbURL = "jdbc:postgresql://localhost:5432/tours";
    @Getter
    private String dbUser = "admin";
    @Getter
    private String dbPassword = "admin123";
    @Getter
    private Locale locale = Locale.GERMAN;


    public static ConfigManager getInstance(){
        if(instance == null)
            instance = new ConfigManager();
        return instance;
    }

    public DbConfig loadDbConfigFromFile(String path) throws IOException {
        FileReader fr = new FileReader(path);
        StringBuilder sb = new StringBuilder();
        int i;

        while((i = fr.read()) != -1)
            sb.append((char)i);

        return Jackson.getInstance().ObjectFromJSON(sb.toString(), DbConfig.class);
    }
}
