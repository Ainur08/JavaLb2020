//package ru.itis.rabbitmq.producers;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.rabbitmq.client.Channel;
//import com.rabbitmq.client.Connection;
//import com.rabbitmq.client.ConnectionFactory;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.util.concurrent.TimeoutException;
//
//@Component
//public class TopicProducer {
//    private final static String PNG_ROUTING_KEY = "files.images.png";
//    private final static String JPG_ROUTING_KEY = "files.images.jpg";
//
//    private final static String FILES_EXCHANGE = "files_topic_exchange";
//    private final static String EXCHANGE_TYPE = "topic";
//
//    private Channel channel;
//
//    public TopicProducer() {
//        ConnectionFactory connectionFactory = new ConnectionFactory();
//        connectionFactory.setHost("localhost");
//        try {
//            Connection connection = connectionFactory.newConnection();
//            channel = connection.createChannel();
//
//            channel.exchangeDeclare(FILES_EXCHANGE, EXCHANGE_TYPE);
//        } catch (IOException | TimeoutException e) {
//            throw new IllegalArgumentException();
//        }
//    }
//
//    public void dataOfPassports(String currentFile) {
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            String json = objectMapper.writeValueAsString(currentFile);
//            String currentRouting = currentFile.substring(currentFile.lastIndexOf("."));
//
//            if (currentRouting.equals(".jpeg")) {
//                currentRouting = ".jpg";
//            }
//
//            switch (currentRouting) {
//                case ".jpg":
//                    currentRouting = JPG_ROUTING_KEY;
//                    break;
//                case ".png":
//                    currentRouting = PNG_ROUTING_KEY;
//                    break;
//            }
//
//            channel.basicPublish(FILES_EXCHANGE, currentRouting, null, json.getBytes());
//        } catch (IOException e) {
//            throw new IllegalStateException(e);
//        }
//    }
//}
