package ru.itis.rabbitmq.producers;

public interface FanoutProducer {
    void data(byte[] bytes);
}
