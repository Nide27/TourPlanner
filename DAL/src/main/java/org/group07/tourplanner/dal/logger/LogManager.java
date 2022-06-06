package org.group07.tourplanner.dal.logger;

public class LogManager {

    private static final ILoggerWrapper LOGGER = LoggerFactory.getLogger();

    public static ILoggerWrapper getLogger(){
        return LOGGER;
    }
}
