package ru.itis.hateoas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.hateoas.models.Company;
import ru.itis.hateoas.models.Vacancy;
import ru.itis.hateoas.repositories.CompaniesRepository;
import ru.itis.hateoas.repositories.VacanciesRepository;

@Service
public class VacancyServiceImpl implements VacancyService {
    @Autowired
    private VacanciesRepository vacanciesRepository;

    @Autowired
    private CompaniesRepository companiesRepository;

    @Override
    public Vacancy createVacancy(Vacancy vacancy, long company_id) {
        Company company = companiesRepository.findById(company_id).orElseThrow(IllegalArgumentException::new);

        vacancy.setCompany(company);
        vacancy.createVacancy();
        vacanciesRepository.save(vacancy);

        return vacancy;
    }
}
