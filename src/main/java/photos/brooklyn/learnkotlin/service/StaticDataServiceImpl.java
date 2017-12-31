package photos.brooklyn.learnkotlin.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StaticDataServiceImpl implements StaticDataService {
    @Value("classpath:db.json")
    private Resource dbResource;

    private Map<String, List<Map<String, ?>>> db = new HashMap<>();

    @Override
    public List<Map<String, ?>> getData(final String key) {
        if(key==null) throw new IllegalArgumentException("Key can't be null");
        this.getDatabase().get(key);
        return db.get(key);
    }

    private synchronized Map<String, List<Map<String, ?>>> getDatabase(){
        if(db.size()==0){
            try {
                db  = new ObjectMapper().readValue(dbResource.getInputStream(),Map.class);
            } catch (IOException e) {
                throw new IllegalStateException("DB file trouble: "+dbResource.getFilename(),e);
            }
        }
        return db;
    }
}
