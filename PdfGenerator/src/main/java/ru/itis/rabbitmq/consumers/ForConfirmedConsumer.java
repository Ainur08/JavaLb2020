package ru.itis.rabbitmq.consumers;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import ru.itis.rabbitmq.dto.UserDto;
import ru.itis.rabbitmq.generate.PdfGenerator;
import ru.itis.rabbitmq.generate.PdfGeneratorImpl;
import ru.itis.rabbitmq.producers.FanoutProducer;
import ru.itis.rabbitmq.producers.FanoutProducerImpl;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@SpringBootApplication
@ComponentScan({
        "ru.itis.rabbitmq.consumers",
        "ru.itis.rabbitmq.generate",
        "ru.itis.rabbitmq.producers"
})
public class ForConfirmedConsumer implements CommandLineRunner {
    private final static String CONFIRMED_QUEUE = "confirmed_queue";

    @Autowired
    private FanoutProducer fanoutProducer;

    public static void main(String[] args) {
        new SpringApplicationBuilder(ForConfirmedConsumer.class)
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

            channel.basicConsume(CONFIRMED_QUEUE, false, (consumerTag, message) -> {
                try {
                    fanoutProducer.data(message.getBody());

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
