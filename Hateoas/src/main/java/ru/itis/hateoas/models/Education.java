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
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String country;
    private String university;
    private String specialization;
    private String stateOfEducation;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public void createEducation() {
        if (this.stateOfEducation.equals("Not_created")) {
            this.stateOfEducation = "Created";
        } else if (this.stateOfEducation.equals("Deleted")) {
            throw new IllegalStateException();
        }
    }
}
