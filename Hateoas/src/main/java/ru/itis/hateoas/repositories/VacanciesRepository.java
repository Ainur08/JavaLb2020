package ru.itis.hateoas.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.itis.hateoas.models.Vacancy;

@RepositoryRestResource
public interface VacanciesRepository extends JpaRepository<Vacancy, Long> {
    @RestResource(path = "created", rel = "created")
    @Query("from Vacancy vacancy where vacancy.stateOfVacancy = 'Created'")
    Page<Vacancy> findAllCreated(Pageable pageable);
}
