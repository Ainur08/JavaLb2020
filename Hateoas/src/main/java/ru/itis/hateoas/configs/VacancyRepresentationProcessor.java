package ru.itis.hateoas.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import ru.itis.hateoas.controllers.VacancyController;
import ru.itis.hateoas.models.Vacancy;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class VacancyRepresentationProcessor implements RepresentationModelProcessor<EntityModel<Vacancy>> {
    @Autowired
    private RepositoryEntityLinks links;

    @Override
    public EntityModel<Vacancy> process(EntityModel<Vacancy> model) {
        Vacancy vacancy = model.getContent();

        if (vacancy != null && vacancy.getStateOfVacancy().equals("Not_created")) {
            model.add(linkTo(methodOn(VacancyController.class)
                    .createVacancy(vacancy)).withRel("create"));
        }

        if (vacancy != null && vacancy.getStateOfVacancy().equals("Created")) {
            model.add(links.linkToItemResource(Vacancy.class, vacancy.getId()).
                    withRel("delete"));
        }
        return model;
    }
}
