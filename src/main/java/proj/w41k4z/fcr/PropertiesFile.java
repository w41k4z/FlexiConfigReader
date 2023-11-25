package proj.w41k4z.fcr;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import proj.w41k4z.helpers.FileHelper;

public class PropertiesFile implements ConfigurationFile {

    private Properties properties;

    public PropertiesFile() {
        properties = new Properties();
    }

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

    @Override
    public Map<String, Object> getConfig() {
        Map<String, Object> config = new HashMap<>();
        for (String key : properties.stringPropertyNames()) {
            config.put(key, properties.getProperty(key));
        }
        return config;
    }

}
