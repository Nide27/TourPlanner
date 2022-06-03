package org.group07.tourplanner.bl;

import org.group07.tourplanner.dal.ConfigManager;

import java.util.ResourceBundle;

public class ResourceManager {

    private static ResourceManager instance;

    private final ResourceBundle bundle;

    public static ResourceManager getInstance(){
        if(instance == null)
            instance = new ResourceManager();
        return instance;
    }

    private ResourceManager(){
        this.bundle = ResourceBundle.getBundle("org.group07.tourplanner.app." + "gui_strings", ConfigManager.getInstance().getLocale());
    }

    public String load(String res){
        return bundle.getString(res);
    }
}
