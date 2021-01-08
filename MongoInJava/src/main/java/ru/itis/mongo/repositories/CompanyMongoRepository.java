package ru.itis.mongo.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.itis.mongo.models.Company;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource
public interface CompanyMongoRepository extends org.springframework.data.mongodb.repository.MongoRepository {
    @RestResource(path = "created", rel = "created")
    Page<Company> findAllCreated(Pageable pageable);
}
