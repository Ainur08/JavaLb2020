package ru.itis.mongo.hateoas.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import ru.itis.mongo.hateoas.controllers.CompanyController;
import ru.itis.mongo.models.Company;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CompanyRepresentationProcessor implements RepresentationModelProcessor<EntityModel<Company>> {
    @Autowired
    private RepositoryEntityLinks links;

    @Override
    public EntityModel<Company> process(EntityModel<Company> model) {
        Company company = model.getContent();

        if (company != null && company.getStateOfCompany().equals("Not_created")) {
            model.add(linkTo(methodOn(CompanyController.class)
                    .createCompany(company.get_id())).withRel("create"));
        }

        if (company != null && company.getStateOfCompany().equals("Created")) {
            model.add(links.linkToItemResource(Company.class, company.get_id()).
                    withRel("delete"));
        }
        return model;
    }
}
