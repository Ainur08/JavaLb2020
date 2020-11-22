package ru.itis.hateoas.services;

import ru.itis.hateoas.models.User;

public interface ConfirmationService {
    User checkConfirmation(String token);
}
