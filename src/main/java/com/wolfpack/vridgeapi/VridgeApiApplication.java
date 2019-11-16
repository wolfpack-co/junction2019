package com.wolfpack.vridgeapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EntityScan("com.wolfpack.vridgeapi.model")
public class VridgeApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(VridgeApiApplication.class, args);
	}

}
