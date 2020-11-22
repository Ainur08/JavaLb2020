package ru.itis.hateoas.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.itis.hateoas.models.Company;

@RepositoryRestResource
public interface CompaniesRepository extends JpaRepository<Company, Long> {
    @RestResource(path = "created", rel = "created")
    @Query("from Company company where company.stateOfCompany = 'Created'")
    Page<Company> findAllCreated(Pageable pageable);
}
