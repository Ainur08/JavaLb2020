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
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String experience;
    private String stateOfSkill;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public void createSkill() {
        if (this.stateOfSkill.equals("Not_created")) {
            this.stateOfSkill = "Created";
        } else if (this.stateOfSkill.equals("Deleted")) {
            throw new IllegalStateException();
        }
    }
}
