package reportportal.configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PropertyDataReader {
    private static final Properties PROPERTIES;
    private static Logger logger;

    private PropertyDataReader() {
        throw new IllegalStateException("It is prohibited to create instance of " + PropertyDataReader.class);
    }

    static {
        logger = Logger.getLogger(PropertyDataReader.class.getName());
        PROPERTIES = new Properties();
        try (InputStream stream = new FileInputStream("src/resources/test-data.properties")) {
            PROPERTIES.load(stream);
        } catch (IOException ioException) {
            logger.log(Level.SEVERE, "An error occurred while performing an IO operation", ioException);
        }
    }

    public static String getDataByKey(String key) {
        Objects.requireNonNull(key, "Properties key can not be null");
        return PROPERTIES.getProperty(key);
    }
}