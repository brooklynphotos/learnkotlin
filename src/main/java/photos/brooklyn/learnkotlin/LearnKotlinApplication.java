package photos.brooklyn.learnkotlin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"photos.brooklyn.learnkotlin"})
public class LearnKotlinApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearnKotlinApplication.class,args);
    }
}
