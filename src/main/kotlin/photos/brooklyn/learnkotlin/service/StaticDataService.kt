package photos.brooklyn.learnkotlin.service

interface StaticDataService {
    /**
     * retrieves data based on the key for that data
     * @param key data key
     * @return data for that key, if there is data
     */
    fun getData(key: String): List<Map<String, *>>?
}
