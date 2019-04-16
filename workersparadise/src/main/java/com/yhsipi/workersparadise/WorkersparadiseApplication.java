package com.yhsipi.workersparadise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"entities"})
@SpringBootApplication
public class WorkersparadiseApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkersparadiseApplication.class, args);
	}

}
