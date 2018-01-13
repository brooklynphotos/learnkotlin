package photos.brooklyn.learnkotlin.service

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.stereotype.Service
import java.io.IOException
import kotlin.collections.*

@Service
class StaticDataServiceImpl : StaticDataService {
    override fun updateDataItem(name: String, id: Int, newDataItem: Map<String, *>): Map<String, *> {
        loadDatabase()
        if(newDataItem["id"] as Int != id) throw IllegalArgumentException("id=$id is not the same as the map=$newDataItem[\"id\"]")
        val table = this.db[name] ?: throw IllegalArgumentException("No such key: "+name)
        val index = table.indexOfFirst { it["id"] as Int == id }
        table[index] = newDataItem
        return newDataItem
    }

    @Synchronized override fun createDataItem(name: String, newDataItem: Map<String, *>): Map<String, *>? {
        loadDatabase()
        val table = this.db[name] ?: throw IllegalArgumentException("No such key: "+name)
        val largest = (table.maxBy { it["id"] as Int }?.get("id") as? Int)?:0
        val newDBItem = HashMap(newDataItem)
        newDBItem["id"] = largest + 1
        table.add(newDBItem)
        return newDBItem
    }

    override fun getDataItem(key: String, id: Int): Map<String, *>? {
        loadDatabase()
        return this.getData(key, mapOf("id" to id))?.get(0)
    }

    @Value("classpath:db.json")
    lateinit private var dbResource: Resource

    val mapper = jacksonObjectMapper()

    private var db: MutableMap<String, MutableList<Map<String, *>>> = HashMap()

    @Synchronized private fun loadDatabase(): Map<String, List<Map<String, *>>> {
        if (db.isEmpty()) {
            try {
                db = mapper.readValue(dbResource.inputStream)
            } catch (e: IOException) {
                throw IllegalStateException("DB file trouble: " + dbResource.filename, e)
            }

        }
        return db
    }

    override fun getData(key: String, attributeFilter: Map<String, *>?): List<Map<String, *>>? {
        this.loadDatabase()
        val fullRes = db.getOrDefault(key,null) ?: return null
        if(attributeFilter==null || attributeFilter.isEmpty()) return fullRes
        return fullRes.filter { map -> this.filtersTrue(map,attributeFilter) }
;    }

    private fun filtersTrue(map: Map<String, *>, filter: Map<String, *>): Boolean {
        for(f in filter){
            val mapV = map[f.key]
            if(mapV==null || mapV != f.value) return false
        }
        return true
    }
}
