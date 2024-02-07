package com.rest.crudOnEmployee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CrudOnEmployeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudOnEmployeeApplication.class, args);
	}


}
