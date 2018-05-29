package cucumber_tests;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.czyz.springbootmongo.SpringbootMongoApplication;

@SpringBootApplication(scanBasePackageClasses = {SpringbootMongoApplication.class})
public class CucumberApplicationContext {
    public static void main(String[] args) {
        SpringApplication.run(CucumberApplicationContext.class, args);
    }
}
