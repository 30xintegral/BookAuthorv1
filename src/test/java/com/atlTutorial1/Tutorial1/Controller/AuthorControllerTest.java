package com.atlTutorial1.Tutorial1.Controller;

import com.atlTutorial1.Tutorial1.dto.AuthorDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;


import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "integration")
@EnableConfigurationProperties
@EnableJpaRepositories
class AuthorControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void givenGetAuthorWhenFoundThenReturnDto() {
        ResponseEntity<AuthorDto> response = restTemplate.getForEntity("http://localhost:"+port+"/author?id=1", AuthorDto.class);

        AuthorDto authorDto = response.getBody();
        assertNotNull(authorDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Test", authorDto.getName());
    }
}