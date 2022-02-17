package com.tus.garbagesorting.garbagesorting.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {
    @Autowired
    private TestRestTemplate template;

    @Test
    public void findAll() throws Exception {
        ResponseEntity<String> response = template.getForEntity("/AllUsers", String.class);
        assertThat(response.getBody()).isEqualTo("Hello Spring Boot");
    }
}