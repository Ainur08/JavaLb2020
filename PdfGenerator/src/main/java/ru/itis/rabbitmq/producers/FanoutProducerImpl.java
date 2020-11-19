package ru.itis.rabbitmq.producers;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Component
public class FanoutProducerImpl implements FanoutProducer {
    // fanout
    private final static String EXCHANGE_NAME = "data";
    private final static String EXCHANGE_TYPE = "fanout";

    public void data(byte[] bytes) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();

            channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);
            channel.basicPublish(EXCHANGE_NAME, "", null, bytes);
        } catch (IOException | TimeoutException e) {
            throw new IllegalStateException(e);
        }
    }
}
