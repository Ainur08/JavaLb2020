package ru.itis.hateoas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itis.hateoas.models.Vacancy;
import ru.itis.hateoas.services.VacancyService;

@RepositoryRestController
public class VacancyController {
    @Autowired
    private VacancyService vacancyService;

    @RequestMapping(value = "/vacancy/create", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<?> createVacancy(Vacancy vacancy) {
        return ResponseEntity.ok(
                EntityModel.of(vacancyService.createVacancy(vacancy, 1L)));
    }
}
