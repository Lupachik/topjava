package ru.javawebinar.topjava.web;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ResourceControllerTest extends AbstractControllerTest {

    @Test
    void getStatusStyle() throws Exception {
        perform(get("/resources/css/style.css"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(new MediaType("text", "css")));
//                .andExpect(content().contentType(MediaType.valueOf("text/css")));

//                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
//        java.lang.AssertionError: Content type expected:<application/json> but was:<text/css>
//                Expected :application/json
//                Actual   :text/css
    }

}
