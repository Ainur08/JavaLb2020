package ru.itis.hateoas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.hateoas.models.Company;
import ru.itis.hateoas.models.User;
import ru.itis.hateoas.repositories.CompaniesRepository;
import ru.itis.hateoas.repositories.UsersRepository;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompaniesRepository companiesRepository;

    @Override
    public Company createCompany(Long id) {
        Company company = companiesRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        company.createCompany();
        companiesRepository.save(company);

        return company;
    }
}
