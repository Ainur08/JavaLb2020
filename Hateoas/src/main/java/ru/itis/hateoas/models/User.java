package ru.itis.hateoas.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "itis_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String hashPassword;
    private String token;

    @Enumerated(value = EnumType.STRING)
    private State state;

    @OneToMany(mappedBy = "user")
    private List<Company> companies;

    @OneToMany(mappedBy = "user")
    private List<Education> educations;

    @OneToMany(mappedBy = "user")
    private List<Skill> skills;
}
