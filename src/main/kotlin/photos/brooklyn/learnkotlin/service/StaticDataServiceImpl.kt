package photos.brooklyn.learnkotlin.service

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.stereotype.Service
import java.io.IOException
import java.util.*

@Service
class StaticDataServiceImpl : StaticDataService {
    @Value("classpath:db.json")
    lateinit private var dbResource: Resource

    val mapper = jacksonObjectMapper()

    private var db: Map<String, List<Map<String, *>>> = HashMap()

    private fun loadDatabase(): Map<String, List<Map<String, *>>> {
        synchronized(db){
            if (db.isEmpty()) {
                try {
                    db = mapper.readValue(dbResource.inputStream)
                } catch (e: IOException) {
                    throw IllegalStateException("DB file trouble: " + dbResource.filename, e)
                }

            }
        }
        return db
    }

    override fun getData(key: String): List<Map<String, *>>? {
        this.loadDatabase()
        return db.getOrDefault(key,null);
    }
}
