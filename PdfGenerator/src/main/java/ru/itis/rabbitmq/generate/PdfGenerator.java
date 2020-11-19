package ru.itis.rabbitmq.generate;

import ru.itis.rabbitmq.dto.UserDto;

public interface PdfGenerator {
    void generatePdfDocs(UserDto user, String templateFileName);
}
