package ru.itis.mongo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "company")
public class Company {
    @Id
    private String _id;

    private String activity;
    private String country;
    private String name;
    private String description;
    private String phone;
    private String stateOfCompany;

    public void createCompany() {
        if (this.stateOfCompany.equals("Not_created")) {
            this.stateOfCompany = "Created";
        } else if (this.stateOfCompany.equals("Deleted")) {
            throw new IllegalStateException();
        }
    }
}
