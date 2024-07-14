package net.bit.reviewer.localization;

import net.bit.reviewer.Main;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;

public class I18n {
    private static final HashMap<String, HashMap<String, String>> localizations = new HashMap<>();

    public static void init() {
        Main.logger.info("Start loading localizations");
        URL localizationPath = Main.class.getResource("/localization");
        if (localizationPath != null) {
            File localizationDir = new File(localizationPath.getPath());
            if (localizationDir.exists() && localizationDir.isDirectory()) {
                File[] files = localizationDir.listFiles();
                if (files != null) {
                    for (File file : files) {
                        String fileName = file.getName().split("\\.")[0];
                        if (file.getName().contains(".lang")) {
                            try {
                                List<String> list = Files.readAllLines(file.toPath());
                                if (!list.isEmpty()) {
                                    HashMap<String, String> locales = new HashMap<>();
                                    for (String line : list) {
                                        if (line.contains("=")) {
                                            String[] arr = line.split("=");
                                            locales.put(arr[0], arr[1]);
                                        }
                                    }
                                    localizations.put(fileName, locales);
                                } else {
                                    Main.logger.warning(fileName + " localization has empty keys");
                                }
                            } catch (IOException e) {
                                Main.logger.info(e.getMessage());
                            }
                        }
                    }
                }
            }
        } else {
            Main.logger.error("Not found directory with locales");
        }
        if (!localizations.isEmpty()) {
            Main.logger.info("Done loading localizations");
        } else {
            Main.logger.error("Localizations is empty!");
        }
    }

    public static String format(String locale, String key) {
        return format(locale, key, (String) null);
    }

    public static String format(String locale, String key, String... args) {
        if (localizations.isEmpty()) {
            return "LOCALE-ERROR";
        }
        HashMap<String, String> locales = localizations.get(locale);
        if (locales == null) {
            locales = localizations.get("en");
        }

        String text = locales.get(key);
        if (args != null) {
            return String.format(text, (Object[]) args);
        } else {
            return text;
        }
    }

}
