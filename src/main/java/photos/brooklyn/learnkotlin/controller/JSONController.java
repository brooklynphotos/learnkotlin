package photos.brooklyn.learnkotlin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import photos.brooklyn.learnkotlin.service.StaticDataService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/staticjson")
public class JSONController {
    @Autowired
    private StaticDataService svc;

    @GetMapping("/{name}")
    public List<Map<String, ?>> getData(@PathVariable final String name){
        return svc.getData(name);
    }
}
