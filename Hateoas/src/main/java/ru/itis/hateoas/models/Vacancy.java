package ru.itis.hateoas.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String sale;
    private String stateOfVacancy;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private Company company;

    public void createVacancy() {
        if (this.stateOfVacancy.equals("Not_created")) {
            this.stateOfVacancy = "Created";
        } else if (this.stateOfVacancy.equals("Deleted")) {
            throw new IllegalStateException();
        }
    }
}
