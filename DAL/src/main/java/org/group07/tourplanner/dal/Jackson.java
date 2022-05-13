package org.group07.tourplanner.dal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;

public class Jackson {

    private static Jackson instance;

    @Getter
    private final ObjectMapper objMapper;

    public static Jackson getInstance(){
        if(instance == null)
            instance = new Jackson();
        return instance;
    }

    private Jackson(){
        objMapper = new ObjectMapper();
        objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); //Prevents crash on missing properties
    }

    public <A> A ObjectFromJSON(String src, Class<A> cls) throws JsonProcessingException {
        return objMapper.readValue(src, cls);
    }

}
