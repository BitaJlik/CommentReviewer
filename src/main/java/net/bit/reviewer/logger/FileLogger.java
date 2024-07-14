package net.bit.reviewer.logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileLogger implements ILogger {
    private final FileWriter writer;

    public FileLogger(String filePath) {
        FileWriter fileWriter;
        Path path = Paths.get(filePath);
        File logFile = new File(path.toString());
        try {
            if (logFile.createNewFile()) {
                info("Created log file");
            } else {
                info("Starting log file");
            }
        } catch (IOException e) {
            error(e.getMessage());
        }
        try {
            fileWriter = new FileWriter(logFile);
        } catch (IOException e) {
            error(e.getMessage());
            fileWriter = null;
        }
        writer = fileWriter;
    }

    @Override
    public void info(String message) {
        if (writer == null) return;
        try {
            writer.write(message);
        } catch (IOException e) {
            error(message);
        }
    }

    @Override
    public void debug(String message) {
        if (writer == null) return;
        try {
            writer.write(message);
        } catch (IOException e) {
            error(message);
        }
    }

    @Override
    public void error(String message) {
        if (writer == null) return;
        try {
            writer.write(message);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void warning(String message) {
        if (writer == null) return;
        try {
            writer.write(message);
        } catch (IOException e) {
            error(message);
        }
    }
}
