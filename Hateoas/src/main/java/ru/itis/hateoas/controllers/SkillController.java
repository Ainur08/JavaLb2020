package ru.itis.hateoas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itis.hateoas.models.Company;
import ru.itis.hateoas.models.Skill;
import ru.itis.hateoas.services.SkillService;

@RepositoryRestController
public class SkillController {
    @Autowired
    private SkillService skillService;

    @RequestMapping(value = "/skill/create", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<?> createSkill(Skill skill) {
        return ResponseEntity.ok(
                EntityModel.of(skillService.createSkill(skill, 1L)));
    }
}
