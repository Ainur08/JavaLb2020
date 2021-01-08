package ru.itis.annotation.models;

import ru.itis.annotation.html.HtmlForm;
import ru.itis.annotation.html.HtmlInput;

@HtmlForm(method = "post", action = "/users")
public class User {
    @HtmlInput(type = "text", name = "first_name", placeholder = "Имя")
    private String firstName;

    @HtmlInput(type = "text", name = "last_name", placeholder = "Фамилия")
    private String lastName;

    @HtmlInput(type = "email", name = "email", placeholder = "Почта")
    private String email;

    @HtmlInput(type = "password", name = "password", placeholder = "Пароль")
    private String password;
}