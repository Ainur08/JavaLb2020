package ru.itis.mongo;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
@Configuration
public class MongoInJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongoInJavaApplication.class, args);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(MongoClients.create(), "job");
    }

    @Bean
    public MongoDatabase mongoDatabase() {
        return MongoClients.create().getDatabase("job");
    }
}
