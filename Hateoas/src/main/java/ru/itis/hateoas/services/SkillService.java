package ru.itis.hateoas.services;

import ru.itis.hateoas.models.Skill;

public interface SkillService {
    Skill createSkill(Skill skill, long user_id);
}
