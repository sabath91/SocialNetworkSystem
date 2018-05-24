package pl.czyz.springbootmongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;

@SpringBootApplication
public class SpringbootMongoApplication extends AsyncConfigurerSupport {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMongoApplication.class, args);
    }

}
