package org.group07.tourplanner.dal;

import lombok.Getter;

import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;

public class ConfigManager {

    private static ConfigManager instance;

    @Getter
    private Locale locale = Locale.ENGLISH;


    public static ConfigManager getInstance(){
        if(instance == null)
            instance = new ConfigManager();
        return instance;
    }

    public <T> T loadConfigFromFile(String path, Class<T> cls) throws IOException {
        FileReader fr = new FileReader(path);
        StringBuilder sb = new StringBuilder();
        int i;

        while((i = fr.read()) != -1)
            sb.append((char)i);

        return Jackson.getInstance().ObjectFromJSON(sb.toString(), cls);
    }
}
