package fuchs.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

/**
 * Utility class for JSON serialization/deserialization.
 */
public class JsonUtils {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * Serialize an object to JSON and write to a file.
     */
    public static void toJson(File file, Object obj) {
        try {
            MAPPER.writerWithDefaultPrettyPrinter().writeValue(file, obj);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to save JSON to file: " + file.getName());
        }
    }

    /**
     * Deserialize JSON from a file to a given class type.
     */
    public static <T> T fromJson(File file, Class<T> clazz) {
        try {
            return MAPPER.readValue(file, clazz);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load JSON from file: " + file.getName());
        }
    }
}
