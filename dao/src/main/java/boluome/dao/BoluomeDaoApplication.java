package boluome.dao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

/**
 * 菠萝蜜Dao应用程序入口
 * Boluome WebApplication Entry
 */
@SpringBootApplication(exclude = {MongoAutoConfiguration.class,MongoDataAutoConfiguration.class})
public class BoluomeDaoApplication {
    public static void main(String[] args) {
        SpringApplication.run(BoluomeDaoApplication.class, args);
    }
}
