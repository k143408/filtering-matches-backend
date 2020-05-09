package com.code.exercise.matches;

import com.code.exercise.matches.service.UserLoaderService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class FilteringMatchesApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilteringMatchesApplication.class, args);
	}


	// execute once application starts.
	@Profile("!test")
	@Bean
	public CommandLineRunner commandLineRunner(UserLoaderService userLoaderService){
		return args -> userLoaderService.execute();
	}

}
