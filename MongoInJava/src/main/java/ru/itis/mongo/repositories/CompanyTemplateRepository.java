package ru.itis.mongo.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ru.itis.mongo.models.Company;
import java.util.List;
import java.util.Optional;

@Repository
public class CompanyTemplateRepository implements CrudRepository<Company, String>{
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void save (Company company) {
        mongoTemplate.save(company, "company");
    }

    @Override
    public void update(Company company){
        mongoTemplate.save(company, "company");
    }

    @Override
    public void delete(String id) {
        mongoTemplate.remove(new Query(Criteria.where("_id").is(id)), "company");
    }

    @Override
    public Optional<Company> findById(String id) {
        return Optional.of(mongoTemplate.findById(id, Company.class, "company"));
    }

    @Override
    public List<Company> findAll() {
        return mongoTemplate.findAll(Company.class, "company");
    }
}
