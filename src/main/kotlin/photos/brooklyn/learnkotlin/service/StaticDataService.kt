package photos.brooklyn.learnkotlin.service

/**
 * service to use a static system
 */
interface StaticDataService {
    /**
     * retrieves data based on the key for that data
     * @param key data key
     * @param attributeFilter optional filter on the list
     * @return data for that key, if there is data
     */
    fun getData(key: String, attributeFilter: Map<String, *>?): List<Map<String, *>>?
}
