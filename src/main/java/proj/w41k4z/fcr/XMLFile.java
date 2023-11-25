package proj.w41k4z.fcr;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import proj.w41k4z.helpers.FileHelper;

/**
 * An implementation of {@link ConfigurationFile} for XML files.
 * You can use this class to load XML files. Designed for Attribute-oriented XML
 * so only the text content as value of the
 * property (given in the constructor) tag will be loaded with the attribut
 * {@code name} as key.
 * 
 * Example:
 * 
 * {@code
 * <configuration>
 *    <property name="key1">value1</property>
 *    <property name="key2">value2</property>
 * </configuration>
 * }
 * 
 */
public class XMLFile implements ConfigurationFile {

    /**
     * The properties containing the configuration file.
     */
    private Properties properties;

    /**
     * The name of the property tag.
     */
    private String propertyTagName;

    /**
     * Create a new instance of {@link XMLFile}.
     * 
     * @param propertyTagName The name of the property tag.
     */
    public XMLFile(String propertyTagName) {
        properties = new Properties();
        this.propertyTagName = propertyTagName;
    }

    /**
     * Load the XML file and set it to the properties field.
     * 
     * @param filePath Path to the XML file.
     * @throws ParserConfigurationException If an error occurred while parsing the
     *                                      XML file.
     * @throws SAXException                 If an error occurred while parsing the
     *                                      XML file.
     * @throws IOException                  If an error occurred while reading the
     *                                      file.
     */
    @Override
    public void load(String filePath) throws ParserConfigurationException, SAXException, IOException {
        File configFile = new File(filePath);
        if (FileHelper.getFileExtension(configFile).equals("xml")) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(configFile);
            Element root = doc.getDocumentElement();
            NodeList propertyNodes = root.getElementsByTagName(propertyTagName);

            for (int i = 0; i < propertyNodes.getLength(); i++) {
                Node propertyNode = propertyNodes.item(i);
                if (propertyNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element propertyElement = (Element) propertyNode;
                    String name = propertyElement.getAttribute("name");
                    String value = propertyElement.getTextContent();
                    properties.setProperty(name, value);
                }
            }
        } else {
            throw new IOException("File is not a XML file.");
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
