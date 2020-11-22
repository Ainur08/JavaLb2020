package ru.itis.hateoas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.itis.hateoas.models.Company;
import ru.itis.hateoas.models.State;
import ru.itis.hateoas.models.User;
import ru.itis.hateoas.models.Vacancy;
import ru.itis.hateoas.repositories.CompaniesRepository;
import ru.itis.hateoas.repositories.UsersRepository;

import static java.util.Arrays.asList;
import static ru.itis.hateoas.models.State.NOT_CONFIRMED;

@SpringBootApplication
public class HateoasApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(HateoasApplication.class, args);

        CompaniesRepository companiesRepository = context.getBean(CompaniesRepository.class);
        UsersRepository usersRepository = context.getBean(UsersRepository.class);

        Vacancy java = Vacancy.builder()
                .description("Good")
                .name("java developer")
                .sale("45000")
                .stateOfVacancy("Created")
                .build();

        Company simbirsoft = Company.builder()
                .country("Russia")
                .stateOfCompany("Not_created")
                .vacancies(asList(java))
                .activity("Hotel service")
                .name("simbirsoft")
                .build();

        User user = User.builder()
                .name("Ainur")
                .token("123")
                .state(NOT_CONFIRMED)
                .build();

        usersRepository.save(user);
        companiesRepository.saveAll(asList(simbirsoft));
    }
}
