package ru.itis.hateoas.services;

import ru.itis.hateoas.models.Education;

public interface EducationService {
    Education createEducation(Education education, long user_id);
}
