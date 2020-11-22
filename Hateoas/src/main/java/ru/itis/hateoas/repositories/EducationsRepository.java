package ru.itis.hateoas.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.itis.hateoas.models.Education;

@RepositoryRestResource
public interface EducationsRepository extends JpaRepository<Education, Long> {
    @RestResource(path = "created", rel = "created")
    @Query("from Education education where education.stateOfEducation = 'Created'")
    Page<Education> findAllCreated(Pageable pageable);
}
