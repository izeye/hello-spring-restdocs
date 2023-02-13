package com.izeye.helloworld.springrestdocs;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Documentation tests for {@link PersonController}.
 *
 * @author Johnny Lim
 */
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
class PersonApiDocumentationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void documentSearch() throws Exception {
        String firstName = "Johnny";
        this.mockMvc.perform(get("/persons/search?firstName={firstName}", firstName))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("persons",
                        queryParameters(parameterWithName("firstName").description("First name.")),
                        responseFields(fieldWithPath("[]").type(JsonFieldType.ARRAY).description("List of persons."),
                                fieldWithPath("[].firstName").type(JsonFieldType.STRING).description("First name."),
                                fieldWithPath("[].lastName").type(JsonFieldType.STRING).description("Last name."))));
    }

}
