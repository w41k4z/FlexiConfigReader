package proj.w41k4z.fcr;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import proj.w41k4z.helpers.FileHelper;

/**
 * An implementation of {@link ConfigurationFile} for properties files.
 * You can use this class to load properties files.
 * 
 * Example:
 * 
 * {@code
 * key1=value1
 * key2=value2
 * }
 */
public class PropertiesFile implements ConfigurationFile {

    /**
     * The properties object that will be used when the getConfig method is called.
     */
    private Properties properties;

    /**
     * Create a new instance of {@link PropertiesFile}.
     */
    public PropertiesFile() {
        properties = new Properties();
    }

    /**
     * Load the properties file and set it to the properties field.
     * 
     * @param filePath Path to the properties file.
     * @throws FileNotFoundException If the file was not found.
     * @throws IOException           If an error occurred while reading the file.
     */
    @Override
    public void load(String filePath) throws FileNotFoundException, IOException {
        File configFile = new File(filePath);
        if (FileHelper.getFileExtension(configFile).equals("properties")) {
            try (FileInputStream fis = new FileInputStream(configFile)) {
                properties.load(fis);
            }
        } else {
            throw new IOException("File is not a properties file.");
        }
    }

    /**
     * Get the loaded configuration from the properties field.
     * 
     * @return The configuration as a map.
     */
    @Override
    public Map<String, Object> getConfig() {
        Map<String, Object> config = new HashMap<>();
        for (String key : properties.stringPropertyNames()) {
            config.put(key, properties.getProperty(key));
        }
        return config;
    }
}
