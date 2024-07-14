package net.bit.reviewer.logger;


import net.bit.reviewer.util.Colors;

public class ConsoleLogger implements ILogger {

    @Override
    public void info(String message) {
        System.out.printf("[%S] %s%n", Thread.currentThread().getName(), message);
    }

    @Override
    public void debug(String message) {
        System.out.printf("[%S] %s%n", Thread.currentThread().getName(), Colors.withReset(Colors.GREEN, message));
    }

    @Override
    public void error(String message) {
        System.out.printf("[%S] %s%n", Thread.currentThread().getName(), Colors.withReset(Colors.RED, message));
    }

    @Override
    public void warning(String message) {
        System.out.printf("[%S] %s%n", Thread.currentThread().getName(), Colors.withReset(Colors.YELLOW, message));
    }
}
