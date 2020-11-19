package ru.itis.rabbitmq.producers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.rabbitmq.dto.UserDto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

@Component
public class DirectProducerImpl implements DirectProducer {
    // direct
    private final static String NOT_CONFIRMED_ROUTING_KEY = "not_confirmed";
    private final static String CONFIRMED_ROUTING_KEY = "confirmed";

    private final static String NOT_CONFIRMED_QUEUE = "not_confirmed_queue";
    private final static String CONFIRMED_QUEUE = "confirmed_queue";

    private final static String EXCHANGE_NAME_DIRECT = "passports";
    private final static String EXCHANGE_TYPE_DIRECT = "direct";

    @Autowired
    private ObjectMapper objectMapper;

    public void dataOfPassports(UserDto userDto) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME_DIRECT, EXCHANGE_TYPE_DIRECT);

            channel.queueBind(CONFIRMED_QUEUE, EXCHANGE_NAME_DIRECT, CONFIRMED_ROUTING_KEY);
            channel.queueBind(NOT_CONFIRMED_QUEUE, EXCHANGE_NAME_DIRECT, NOT_CONFIRMED_ROUTING_KEY);

            String json = objectMapper.writeValueAsString(userDto);

            String currentRouting = userDto.getState();
            if (currentRouting.equals("confirmed")) {
                currentRouting = "confirmed";
            }
            channel.basicPublish(EXCHANGE_NAME_DIRECT, currentRouting, null, json.getBytes());
        } catch (IOException | TimeoutException e) {
            throw new IllegalStateException(e);
        }
    }
}
