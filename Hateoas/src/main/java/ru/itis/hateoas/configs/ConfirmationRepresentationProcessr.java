package ru.itis.hateoas.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import ru.itis.hateoas.controllers.ConfirmationController;
import ru.itis.hateoas.models.User;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ConfirmationRepresentationProcessr implements RepresentationModelProcessor<EntityModel<User>> {
    @Autowired
    private RepositoryEntityLinks links;

    @Override
    public EntityModel<User> process(EntityModel<User> model) {
        User user = model.getContent();

        model.add(linkTo(methodOn(ConfirmationController.class)
                .activate(user.getToken())).withRel("activate"));

        return model;
    }
}
