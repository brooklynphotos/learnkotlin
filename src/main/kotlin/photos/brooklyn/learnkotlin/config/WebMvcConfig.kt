package photos.brooklyn.learnkotlin.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import photos.brooklyn.learnkotlin.spring.LagInterceptor


@Configuration
class WebMvcConfig : WebMvcConfigurerAdapter() {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(LagInterceptor())
    }
}