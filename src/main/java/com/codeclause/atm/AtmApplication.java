package com.codeclause.atm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.codeclause.atm")
public class AtmApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtmApplication.class, args);
	}

	// @Bean
	// CommandLineRunner runner() {
	// 	return args -> {
	// 		user_ent us1 = new user_ent("ayush","sahu",90303,399);
	// 	};
	// }

}
