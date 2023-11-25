package proj.w41k4z.fcr;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

/**
 * Created by w41k on 14.06.14.
 * 
 * Interface for configuration files.
 * You can use this interface to implement your own configuration file.
 * All implementing class: {@link PropertiesFile}, {@link JSONFile},
 * {@link XMLFile}...
 */
public interface ConfigurationFile {

    /**
     * Load the configuration file.
     * 
     * @param filePath Path to the configuration file.
     * @throws Exception Depending on the implementation class.
     */
    public void load(String filePath) throws Exception;

    /**
     * Get the loaded configuration.
     * 
     * @return The configuration as a map.
     */
    public Map<String, Object> getConfig();
}
