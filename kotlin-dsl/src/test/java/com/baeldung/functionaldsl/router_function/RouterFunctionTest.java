package com.baeldung.functionaldsl.routerfunction;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.baeldung.functionaldsl.routerfunction.Application;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = Application.class)
@ContextConfiguration(classes = Application.class)
public class RouterFunctionTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void test() throws Exception {
        Map<String, Object> result = objectMapper.readValue(mockMvc
          .perform(get("/endpoint/anything").header("X-age", 22).param("name", "Mikhail"))
          .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
          .andReturn()
          .getResponse()
          .getContentAsString(), new TypeReference<Map<String, Object>>() {
        });

        Assertions.assertThat(result).containsKeys("name", "age", "country");
    }
}
