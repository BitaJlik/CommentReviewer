package net.bit.reviewer.logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CompositeLogger implements ILogger {
    private final List<ILogger> loggers = new ArrayList<>();

    public CompositeLogger(ILogger... loggers) {
        Collections.addAll(this.loggers, loggers);
    }

    @Override
    public void info(String message) {
        for (ILogger logger : loggers) {
            logger.info(message);
        }
    }

    @Override
    public void debug(String message) {
        for (ILogger logger : loggers) {
            logger.debug(message);
        }
    }

    @Override
    public void error(String message) {
        for (ILogger logger : loggers) {
            logger.error(message);
        }
    }

    @Override
    public void warning(String message) {
        for (ILogger logger : loggers) {
            logger.warning(message);
        }
    }
}
