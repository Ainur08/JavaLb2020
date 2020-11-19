package ru.itis.rabbitmq.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private String first_name;
    private String last_name;
    private String numberOfPassport;
    private String age;
    private String date;
    private String state;
}
