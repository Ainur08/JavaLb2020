package ru.itis.hateoas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.itis.hateoas.models.User;

import java.util.Optional;

@RepositoryRestResource
public interface UsersRepository extends JpaRepository<User, Long> {

    @RestResource(rel = "token", path = "activate")
    Optional<User> findByToken(String token);
}
