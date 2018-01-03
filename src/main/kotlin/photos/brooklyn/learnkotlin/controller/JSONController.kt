package photos.brooklyn.learnkotlin.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import photos.brooklyn.learnkotlin.service.StaticDataService

@RestController
@RequestMapping("/staticjson")
class JSONController(val svc: StaticDataService) {

    @GetMapping("/{name}")
    fun getData(@PathVariable name: String): List<Map<String, *>>? {
        return svc.getData(name)
    }
}
