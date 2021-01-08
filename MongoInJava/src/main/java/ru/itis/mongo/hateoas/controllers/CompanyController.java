package ru.itis.mongo.hateoas.controllers;

import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itis.mongo.hateoas.services.CompanyService;

@RepositoryRestController
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @RequestMapping(value = "/companies/{company_id}/create", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<?> createCompany(@PathVariable("company_id") String company_id) {
        return ResponseEntity.ok(
                EntityModel.of(companyService.createCompany(company_id)));
    }
}
