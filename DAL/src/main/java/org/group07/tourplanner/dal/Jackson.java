package org.group07.tourplanner.dal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
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
        objMapper.registerModule(new JSR310Module());
        objMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); //Prevents crash on missing properties
    }

    public <A> A ObjectFromJSON(String src, Class<A> cls) throws JsonProcessingException {
        return objMapper.readValue(src, cls);
    }

    public String JSONFromObject(Object obj, boolean pretty) throws JsonProcessingException {
        ObjectWriter objWriter = objMapper.writer();

        if(pretty)
            objWriter = objWriter.with(SerializationFeature.INDENT_OUTPUT);

        return objWriter.writeValueAsString(obj);
    }

}
