package com.example.sevenwingsbudget.controller;

import com.example.sevenwingsbudget.dto.response.AuthorResponse;
import com.example.sevenwingsbudget.service.AuthorService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthorController.class)
@AutoConfigureMockMvc
class AuthorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AuthorService authorService;

    @Test
    void testSave_WhenFullNameIsValid() throws Exception {
        AuthorResponse mockResponse = new AuthorResponse(
                1L,"Yazgevich Anton Alexandrovich","2025-01-12T10:00:00");

        Mockito.when(authorService.save(anyString())).thenReturn(mockResponse);

        mockMvc.perform(get("/author")
                        .param("fullName", "Yazgevich Anton Alexandrovich")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.fullName").value("Yazgevich Anton Alexandrovich"))
                .andExpect(jsonPath("$.createdAt").value("2025-01-12T10:00:00"));

        Mockito.verify(authorService).save("Yazgevich Anton Alexandrovich");
    }

    @Test
    void testValidationFullName_WhenTooShort() throws Exception {
        mockMvc.perform(get("/author")
                        .param("fullName", "Short")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testValidationFullName_WhenTooLong() throws Exception {
        String tooLongName = "a".repeat(129);

        mockMvc.perform(get("/author")
                        .param("fullName", tooLongName)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

}