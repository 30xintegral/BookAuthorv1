package com.atlTutorial1.Tutorial1.Controller;

import com.atlTutorial1.Tutorial1.Tutorial1Application;
import com.atlTutorial1.Tutorial1.config.SecurityConfig;
import com.atlTutorial1.Tutorial1.dto.AuthorDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;


import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(classes = Tutorial1Application.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "integration")
@EnableConfigurationProperties
@ContextConfiguration(classes = {SecurityConfig.class})
@EnableJpaRepositories(basePackages = {"com.atlTutorial1.Tutorial1.Repository"})
@ComponentScan(basePackages = {"com.atlTutorial1.Tutorial1.config", "com.atlTutorial1.Tutorial1.security"})
@EnableAutoConfiguration
class AuthorControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    @Sql(scripts = "classpath:sql/authors.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void givenGetAuthorWhenFoundThenReturnDto() {
        AuthorDto response = restTemplate.getForObject("http://localhost:"+port+"/author?id=1", AuthorDto.class);

        AuthorDto authorDto = response;


//        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(authorDto);
        assertEquals("Test", authorDto.getName());
    }
}