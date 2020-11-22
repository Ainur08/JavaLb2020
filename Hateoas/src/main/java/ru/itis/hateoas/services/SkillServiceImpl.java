package ru.itis.hateoas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.hateoas.models.Skill;
import ru.itis.hateoas.models.User;
import ru.itis.hateoas.repositories.SkillsRepository;
import ru.itis.hateoas.repositories.UsersRepository;

@Service
public class SkillServiceImpl implements SkillService {
    @Autowired
    private SkillsRepository skillsRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public Skill createSkill(Skill skill, long user_id) {
        User user = usersRepository.findById(user_id).orElseThrow(IllegalArgumentException::new);

        skill.setUser(user);
        skill.createSkill();
        skillsRepository.save(skill);

        return skill;
    }
}
