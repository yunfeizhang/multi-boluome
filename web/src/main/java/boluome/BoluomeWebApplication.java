package boluome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

/**
 * 菠萝蜜Web应用程序入口
 * Boluome WebApplication Entry
 */
@SpringBootApplication(exclude = {MongoAutoConfiguration.class,MongoDataAutoConfiguration.class})
public class BoluomeWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(BoluomeWebApplication.class, args);
    }
}
