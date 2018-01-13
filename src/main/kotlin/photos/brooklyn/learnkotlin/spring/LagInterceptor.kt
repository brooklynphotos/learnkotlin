package photos.brooklyn.learnkotlin.spring

import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import java.lang.Exception
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class LagInterceptor: HandlerInterceptor{
    override fun preHandle(req: HttpServletRequest?, resp: HttpServletResponse?, options: Any?): Boolean {
        Thread.sleep(1000)
        return true
    }

    override fun postHandle(req: HttpServletRequest?, resp: HttpServletResponse?, p2: Any?, p3: ModelAndView?) {
    }

    override fun afterCompletion(req: HttpServletRequest?, resp: HttpServletResponse?, p2: Any?, exception: Exception?) {
    }

}