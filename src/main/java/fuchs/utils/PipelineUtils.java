package fuchs.utils;

import java.util.HashMap;
import java.util.Map;

public class PipelineUtils {

    /**
     * Parse a param string like "threshold=128,method=otsu"
     * into a Map { "threshold" -> "128", "method" -> "otsu" }.
     */
    public static Map<String, String> parseParams(String paramsStr) {
        Map<String, String> params = new HashMap<>();
        if (paramsStr == null || paramsStr.isEmpty()) {
            return params;
        }
        String[] kvPairs = paramsStr.split(",");
        for (String kv : kvPairs) {
            String[] splitted = kv.split("=");
            if (splitted.length == 2) {
                params.put(splitted[0], splitted[1]);
            }
        }
        return params;
    }
}
