package com.atlTutorial1.Tutorial1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class Tutorial1Application {


	public static void main(String[] args) {
		SpringApplication.run(Tutorial1Application.class, args);

	}

}
