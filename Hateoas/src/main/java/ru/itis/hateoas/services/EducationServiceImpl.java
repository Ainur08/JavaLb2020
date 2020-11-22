package ru.itis.hateoas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.hateoas.models.Education;
import ru.itis.hateoas.models.User;
import ru.itis.hateoas.repositories.EducationsRepository;
import ru.itis.hateoas.repositories.UsersRepository;

@Service
public class EducationServiceImpl implements EducationService {
    @Autowired
    private EducationsRepository educationsRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public Education createEducation(Education education, long user_id) {
        User user = usersRepository.findById(user_id).orElseThrow(IllegalArgumentException::new);

        education.setUser(user);
        education.createEducation();
        educationsRepository.save(education);

        return education;
    }
}
