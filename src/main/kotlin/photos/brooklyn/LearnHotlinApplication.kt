package photos.brooklyn

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication(scanBasePackages = ["photos.brooklyn.learnkotlin"])
class LearnKotlinApplication

fun main(args: Array<String>) {
    SpringApplication.run(LearnKotlinApplication::class.java, *args)
}
