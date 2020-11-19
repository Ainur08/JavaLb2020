package ru.itis.rabbitmq.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.rabbitmq.dto.UserDto;
import ru.itis.rabbitmq.generate.PdfGeneratorImpl;
import ru.itis.rabbitmq.producers.DirectProducer;

@Controller
public class MainController {
    @Autowired
    private DirectProducer directProducer;

    @GetMapping("/")
    public String getPage() {
        return "main";
    }

    @PostMapping("/")
    public String generate(UserDto userDto) {
        try {
            directProducer.dataOfPassports(userDto);
            return "redirect:/";
        } catch (IllegalStateException e) {
            return "redirect:/?error";
        }
    }
}
