package ru.itis.mongo.repositories;

import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.itis.mongo.models.Company;
import java.util.List;
import java.util.Optional;

@Repository
public class CompanyDriverRepository implements CrudRepository<Company, String>{
    @Autowired
    private MongoDatabase mongoDatabase;


    @Override
    public void save(Company company) {
        Document doc = new Document("name", company.getName())
                .append("activity", company.getActivity())
                .append("country", company.getCountry())
                .append("description", company.getDescription())
                .append("phone", company.getPhone())
                .append("stateOfCompany", company.getStateOfCompany());

        mongoDatabase.getCollection("company").insertOne(doc);
    }

    @Override
    public void delete(String id) {
        mongoDatabase.getCollection("company").deleteOne(new Document("_id", new ObjectId(id)));
    }

    @Override
    public void update(Company company) {

    }

    @Override
    public Optional<Company> findById(String id) {
        return Optional.empty();
    }

    @Override
    public List<Company> findAll() {
        return null;
    }
}
