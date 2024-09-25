package org.yaroglek.patterns.extern.logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Паттерн Singleton
 */
public class Logger {
    private static Logger instance;
    private static final String LOG_FILE = "src/main/java/org/yaroglek/patterns/extern/logger/application.log";

    private Logger() {}

    public static synchronized Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void log(String msg) {
        String logMessage = LocalDateTime.now() + ": " + msg + "\n";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            bw.write(logMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
