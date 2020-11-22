package ru.itis.hateoas.services;

import ru.itis.hateoas.models.Vacancy;

public interface VacancyService {
    Vacancy createVacancy(Vacancy vacancy, long company_id);
}
