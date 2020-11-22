package ru.itis.hateoas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.itis.hateoas.models.Company;
import ru.itis.hateoas.models.User;
import ru.itis.hateoas.services.CompanyService;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class CompaniesTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompanyService companyService;

    @BeforeEach
    public void setUp() {
        when(companyService.createCompany(1L)).thenReturn(createdCompany());
    }

    @Test
    public void coursePublishTest() throws Exception {
        mockMvc.perform(post("/companies/1/create")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(createdCompany().getName()))
                .andExpect(jsonPath("$.country").value(createdCompany().getCountry()))
                .andExpect(jsonPath("$.activity").value(createdCompany().getActivity()))
                .andExpect(jsonPath("$.stateOfCompany").value(createdCompany().getStateOfCompany()))

                .andDo(document("create_company", responseFields(
                        fieldWithPath("name").description("Название компании"),
                        fieldWithPath("country").description("Страна"),
                        fieldWithPath("activity").description("Сфера"),
                        fieldWithPath("stateOfCompany").description("Статус"),
                        subsectionWithPath("_links").description("HATEOAS links")
                ),links(
                        halLinks(),
                        linkWithRel("delete").description("The link to delete resource"))));
    }

    private Company createdCompany() {
        return Company.builder()
                .id(1L)
                .name("Simbirsoft")
                .activity("Web")
                .country("Russia")
                .stateOfCompany("Created")
                .build();
    }
}
