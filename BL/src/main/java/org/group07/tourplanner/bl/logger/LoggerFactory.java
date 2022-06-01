package org.group07.tourplanner.bl.logger;

public class LoggerFactory {

    public static ILoggerWrapper getLogger() {
        var logger = new Log4J2Wrapper();
        logger.initialize();
        return logger;
    }
}
