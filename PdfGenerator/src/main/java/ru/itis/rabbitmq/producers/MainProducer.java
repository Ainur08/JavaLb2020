package ru.itis.rabbitmq.producers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({
        "ru.itis.rabbitmq.producers",
        "ru.itis.rabbitmq.controllers"
})
public class MainProducer {
    public static void main(String[] args) {
        SpringApplication.run(MainProducer.class, args);
    }
}