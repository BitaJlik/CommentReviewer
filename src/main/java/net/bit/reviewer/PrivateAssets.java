package net.bit.reviewer;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Properties;

public class PrivateAssets {
    public static String BOT_TOKEN;
    public static String BOT_NAME;

    public static void init() {
        URL path = Main.class.getResource("/private.properties");
        if (path != null) {
            try {
                Properties properties = new Properties();
                properties.load(Files.newBufferedReader(new File(path.getPath()).toPath()));
                BOT_TOKEN = properties.getProperty("token");
                BOT_NAME = properties.getProperty("name");
            } catch (IOException e) {
                Main.logger.error(e.getMessage());
            }
        }
    }
}
