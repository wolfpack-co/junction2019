package com.wolfpack.vridgeapi;

import com.wolfpack.vridgeapi.config.WebConfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@Import(WebConfig.class)
@EntityScan("com.wolfpack.vridgeapi.model")
public class VridgeApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(VridgeApiApplication.class, args);
	}

}
