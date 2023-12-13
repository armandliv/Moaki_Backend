package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.user.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;


@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(UserRepository repository) {
		return args -> {
			User user = new User(
				"CosminUsername",
				"CosminPassword",
				"CosminName",
				"CosminEmail",
				"CosminBio",
				"CosminProfilePicturePath",
				true,
				List.of("1", "2", "3"),
				List.of("1", "2", "3")
			);
			repository.insert(user);
		};
	}
}
