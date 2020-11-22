package ru.itis.hateoas.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.itis.hateoas.models.Skill;

import java.util.List;

@RepositoryRestResource
public interface SkillsRepository extends JpaRepository<Skill, Long> {
    @RestResource(path = "created", rel = "created")
    @Query("from Skill skill where skill.stateOfSkill = 'Created'")
    Page<Skill> findAllCreated(Pageable pageable);

    @RestResource(rel = "skill", path = "bySkill")
    List<Skill> findAllByName(String skill);
}
