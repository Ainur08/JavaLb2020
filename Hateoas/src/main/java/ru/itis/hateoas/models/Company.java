package ru.itis.hateoas.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String country;
    private String activity;
    private String stateOfCompany;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "company")
    private List<Vacancy> vacancies;

    public void createCompany() {
        if (this.stateOfCompany.equals("Not_created")) {
            this.stateOfCompany = "Created";
        } else if (this.stateOfCompany.equals("Deleted")) {
            throw new IllegalStateException();
        }
    }
}
