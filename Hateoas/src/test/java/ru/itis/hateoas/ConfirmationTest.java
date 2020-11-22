package ru.itis.hateoas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.itis.hateoas.models.Company;
import ru.itis.hateoas.models.User;
import ru.itis.hateoas.services.ConfirmationService;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.itis.hateoas.models.State.CONFIRMED;
import static ru.itis.hateoas.models.State.NOT_CONFIRMED;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class ConfirmationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConfirmationService confirmationService;

    @BeforeEach
    public void setUp() {
        when(confirmationService.checkConfirmation("123")).thenReturn(createdUser());
    }

    @Test
    public void checkConfirmation() throws Exception {
        mockMvc.perform(get("/users/activate/123")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(createdUser().getName()))
                .andExpect(jsonPath("$.email").value(createdUser().getEmail()))
                .andExpect(jsonPath("$.hashPassword").value(createdUser().getHashPassword()))
                .andExpect(jsonPath("$.state").value(createdUser().getState().toString()))
                .andExpect(jsonPath("$.token").value(createdUser().getToken()))

                .andDo(document("check_confirmation", responseFields(
                        fieldWithPath("name").description("Имя"),
                        fieldWithPath("email").description("Почта"),
                        fieldWithPath("hashPassword").description("Пароль"),
                        fieldWithPath("state").description("Статус"),
                        fieldWithPath("token").description("Токен"),
                        subsectionWithPath("_links").description("HATEOAS links")
                ), links(
                        halLinks(),
                        linkWithRel("activate").description("The link to activate resource"))));
    }

    private User createdUser() {
        return User.builder()
                .id(1L)
                .name("Ainur")
                .email("ainur@gmail.com")
                .state(CONFIRMED)
                .token("123")
                .hashPassword("0xsadsa132")
                .build();
    }
}
