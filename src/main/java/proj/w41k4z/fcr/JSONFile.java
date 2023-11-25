package proj.w41k4z.fcr;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * An implementation of {@link ConfigurationFile} for JSON files.
 * You can use this class to load JSON files.
 * 
 * Example:
 * 
 * {@code
 * {
 *   "key1": "value1",
 *   "key2": { 
 *        "nested_key": "value2"
 * }
 * }
 * }
 */
public class JSONFile implements ConfigurationFile {

    /**
     * The map containing the configuration.
     */
    private Map<String, Object> configMap;

    /**
     * Create a new instance of {@link JSONFile}.
     */
    public JSONFile() {
        configMap = new HashMap<>();
    }

    /**
     * Load the JSON file and set it to the configMap field.
     * 
     * @param filePath Path to the JSON file.
     * @throws IOException If an error occurred while reading the file.
     */
    @Override
    public void load(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(new File(filePath));
        this.parseJsonNode(rootNode, "");
    }

    /**
     * Get the loaded configuration from the configMap field.
     * 
     * @return The loaded configuration.
     */
    @Override
    public Map<String, Object> getConfig() {
        return configMap;
    }

    /**
     * Parse the JSON node and add the values to the configMap.
     * 
     * @param jsonNode    The JSON node to parse.
     * @param currentPath The current path of the JSON node.
     */
    private void parseJsonNode(JsonNode jsonNode, String currentPath) {
        if (jsonNode.isObject()) {
            Iterator<Entry<String, JsonNode>> fields = jsonNode.fields();
            while (fields.hasNext()) {
                Entry<String, JsonNode> entry = fields.next();
                String fieldName = entry.getKey();
                JsonNode fieldValue = entry.getValue();
                String newPath = currentPath.isEmpty() ? fieldName : currentPath + "." + fieldName;
                this.parseJsonNode(fieldValue, newPath);
            }
        } else {
            configMap.put(currentPath, jsonNode.asText());
        }
    }

}
