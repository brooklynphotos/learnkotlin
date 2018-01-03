package photos.brooklyn.learnkotlin.controller

import org.springframework.web.bind.annotation.*
import photos.brooklyn.learnkotlin.service.StaticDataService
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/staticjson")
class JSONController(val svc: StaticDataService) {

    @GetMapping("/{name}")
    fun getData(@PathVariable name: String, request: HttpServletRequest): List<Map<String, *>>? {
        return svc.getData(name,request.parameterMap.map{entry -> entry.key to entry.value[0] }.toMap())
    }
}
