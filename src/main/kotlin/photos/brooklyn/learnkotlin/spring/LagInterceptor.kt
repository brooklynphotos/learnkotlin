package photos.brooklyn.learnkotlin.spring

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class LagInterceptor: HandlerInterceptorAdapter() {
    override fun preHandle(req: HttpServletRequest?, resp: HttpServletResponse?, options: Any?): Boolean {
        Thread.sleep(1000)
        logger.debug("Done with artificial lag")
        return true
    }

    companion object {
        val logger = LoggerFactory.getLogger(LagInterceptor::class.java)
    }
}