package ru.itis.rabbitmq.consumers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import ru.itis.rabbitmq.dto.UserDto;
import ru.itis.rabbitmq.generate.PdfGenerator;
import ru.itis.rabbitmq.generate.PdfGeneratorImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

@SpringBootApplication
@ComponentScan({
        "ru.itis.rabbitmq.consumers",
        "ru.itis.rabbitmq.generate"
})
public class ForNotConfirmedConsumer implements CommandLineRunner {
    private final static String NOT_CONFIRMED_QUEUE = "not_confirmed_queue";

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PdfGenerator generator;

    public static void main(String[] args) {
        new SpringApplicationBuilder(ForNotConfirmedConsumer.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();

            channel.basicConsume(NOT_CONFIRMED_QUEUE, false, (consumerTag, message) -> {
                try {
                    UserDto userDto = objectMapper.readValue(message.getBody(), UserDto.class);
                    generator.generatePdfDocs(userDto, "enrollment.html");

                    channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
                } catch (IOException e) {
                    System.err.println("FAILED");
                    channel.basicReject(message.getEnvelope().getDeliveryTag(), false);
                }
            }, consumerTag -> {
            });

        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
