package ge.tbc.testautomation.data; // creating conflict 2

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBConfiguration {
    private static final Properties properties = new Properties();

    static {
        try (InputStream inputStream = DBConfiguration.class.getClassLoader().getResourceAsStream("database.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Properties file could not be loaded.");
        }
    }

    public static String getURL() {
        return properties.getProperty("db.url");
    }
}