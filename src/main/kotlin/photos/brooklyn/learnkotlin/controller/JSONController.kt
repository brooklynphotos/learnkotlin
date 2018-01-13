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

    @GetMapping("/{name}/{id}")
    fun getDataItem(@PathVariable name: String, @PathVariable id: Int): Map<String, *>? {
        return svc.getDataItem(name, id)
    }

    @PostMapping("/{name}")
    fun createDataItem(@PathVariable name: String, @RequestBody newDataItem: Map<String, *>): Map<String, *>? {
        return svc.createDataItem(name, newDataItem)
    }

    @PutMapping("/{name}/{id}")
    fun updateDataItem(@PathVariable name: String, @PathVariable id: Int, @RequestBody newDataItem: Map<String, *>): Map<String, *>? {
        return svc.updateDataItem(name, id, newDataItem)
    }
}
