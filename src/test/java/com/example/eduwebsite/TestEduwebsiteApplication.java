package com.example.eduwebsite;

import org.springframework.boot.SpringApplication;

public class TestEduwebsiteApplication {

	public static void main(String[] args) {
		SpringApplication.from(EduwebsiteApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
