package net.bit.reviewer.logger;

import java.util.Objects;

public class BitLogger implements ILogger {
    private ILogger logger;

    public void setLogger(ILogger logger) {
        this.logger = Objects.requireNonNullElseGet(logger, ConsoleLogger::new);
    }

    public void info(String message) {
        if (logger == null) return;
        logger.info(message);
    }

    public void warning(String message) {
        if (logger == null) return;
        logger.warning(message);
    }

    public void error(String message) {
        if (logger == null) return;
        logger.error(message);
    }

    public void debug(String message) {
        if (logger == null) return;
        logger.debug(message);
    }

}
