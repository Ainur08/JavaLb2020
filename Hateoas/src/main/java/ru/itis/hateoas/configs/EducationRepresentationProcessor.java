package ru.itis.hateoas.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import ru.itis.hateoas.controllers.EducationController;
import ru.itis.hateoas.models.Education;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EducationRepresentationProcessor implements RepresentationModelProcessor<EntityModel<Education>> {
    @Autowired
    private RepositoryEntityLinks links;

    @Override
    public EntityModel<Education> process(EntityModel<Education> model) {
        Education education = model.getContent();

        if (education != null && education.getStateOfEducation().equals("Not_created")) {
            model.add(linkTo(methodOn(EducationController.class)
                    .createEducation(education)).withRel("create"));
        }

        if (education != null && education.getStateOfEducation().equals("Created")) {
            model.add(links.linkToItemResource(Education.class, education.getId()).
                    withRel("delete"));
        }
        return model;
    }
}
