package ru.itis.mongo.hateoas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.mongo.models.Company;
import ru.itis.mongo.repositories.CompanyTemplateRepository;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyTemplateRepository companyTemplateRepository;

    @Override
    public Company createCompany(String id) {
        Company company = companyTemplateRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        company.createCompany();
        companyTemplateRepository.save(company);

        return company;
    }
}
