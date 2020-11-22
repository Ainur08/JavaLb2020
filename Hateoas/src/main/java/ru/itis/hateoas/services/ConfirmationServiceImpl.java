package ru.itis.hateoas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.hateoas.models.State;
import ru.itis.hateoas.models.User;
import ru.itis.hateoas.repositories.UsersRepository;

import java.util.Optional;

@Service
public class ConfirmationServiceImpl implements ConfirmationService {
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public User checkConfirmation(String token) {
        Optional<User> optionalUser = usersRepository.findByToken(token);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setState(State.CONFIRMED);
            usersRepository.save(user);
            return user;
        }
        return null;
    }
}
