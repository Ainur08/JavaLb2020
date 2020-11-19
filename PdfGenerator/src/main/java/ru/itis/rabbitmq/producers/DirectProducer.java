package ru.itis.rabbitmq.producers;

import ru.itis.rabbitmq.dto.UserDto;

public interface DirectProducer {
    void dataOfPassports(UserDto userDto);
}
