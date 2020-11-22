package ru.itis.hateoas.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import ru.itis.hateoas.controllers.SkillController;
import ru.itis.hateoas.models.Skill;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class SkillRepresentationProcessor implements RepresentationModelProcessor<EntityModel<Skill>> {
    @Autowired
    private RepositoryEntityLinks links;

    @Override
    public EntityModel<Skill> process(EntityModel<Skill> model) {
        Skill skill = model.getContent();

        if (skill != null && skill.getStateOfSkill().equals("Not_created")) {
            model.add(linkTo(methodOn(SkillController.class)
                    .createSkill(skill)).withRel("create"));
        }

        if (skill != null && skill.getStateOfSkill().equals("Created")) {
            model.add(links.linkToItemResource(Skill.class, skill.getId()).
                    withRel("delete"));
        }
        return model;
    }
}
