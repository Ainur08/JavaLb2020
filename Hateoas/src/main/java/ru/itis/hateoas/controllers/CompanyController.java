package ru.itis.hateoas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.hateoas.models.Company;
import ru.itis.hateoas.services.CompanyService;

@RepositoryRestController
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @RequestMapping(value = "/companies/{company_id}/create", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<?> createCompany(@PathVariable("company_id") Long company_id) {
        return ResponseEntity.ok(
                EntityModel.of(companyService.createCompany(company_id)));
    }
}
