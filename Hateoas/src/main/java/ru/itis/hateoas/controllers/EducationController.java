package ru.itis.hateoas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itis.hateoas.models.Education;
import ru.itis.hateoas.services.EducationService;

@RepositoryRestController
public class EducationController {
    @Autowired
    private EducationService educationService;

    @RequestMapping(value = "/education/create", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<?> createEducation(Education education) {
        return ResponseEntity.ok(
                EntityModel.of(educationService.createEducation(education, 1L)));
    }
}
