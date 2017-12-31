package photos.brooklyn.learnkotlin.service;

import java.util.List;
import java.util.Map;

public interface StaticDataService {
    /**
     * retrieves data based on the key for that data
     * @param key data key
     * @return data for that key
     */
    List<Map<String, ?>> getData(String key);
}
