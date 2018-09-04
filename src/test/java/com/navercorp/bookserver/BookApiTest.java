package com.navercorp.bookserver;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = BookServerApplication.class)
@WebAppConfiguration
public class BookApiTest {

    @Autowired
    WebApplicationContext wac;
    MockMvc mvc;

    @Before
    public void setUp() {
        this.mvc = webAppContextSetup(this.wac)
                .alwaysDo(print(System.out))
                .build();
    }

    @Test
    public void shouldCreate() throws Exception {
        String requestBody = "{\"title\":\"사피엔스\", \"author\":\"유발하라리\"}";

        mvc.perform(
                post("/api/books/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
        )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").value("사피엔스"))
                .andExpect(jsonPath("$.author").value("유발하라리"));
    }

    @Test
    public void shouldUpdate() throws Exception {
        String requestBody = "{\"title\":\"사피엔스\", \"author\":\"유발하라리\"}";

        mvc.perform(
                put("/api/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
        )
                .andExpect(status().isNoContent());
    }

    @Test
    public void shoulDelete() throws Exception {
        mvc.perform(
                delete("/api/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isNoContent());
    }

    @Test
    public void statusShouldBe404WhenBookIsNotExisted() throws Exception {
        mvc.perform(
                get("/api/books/999")
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.detail").exists());
    }

    @Test
    public void statusShouldBe405WhenCallingNotExisingDeleteVerb() throws Exception {
        mvc.perform(
                delete("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isMethodNotAllowed());
    }
}
