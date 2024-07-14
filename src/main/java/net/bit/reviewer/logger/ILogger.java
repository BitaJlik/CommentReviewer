package net.bit.reviewer.logger;

public interface ILogger {
    void info(String message);

    void debug(String message);

    void error(String message);

    void warning(String message);

}
