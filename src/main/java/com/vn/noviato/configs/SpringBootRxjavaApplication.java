package com.vn.noviato.configs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages={"com.vn.noviato"})
@EntityScan(basePackages="com.vn.noviato.model")
@EnableJpaRepositories(basePackages="com.vn.noviato.repositories")
public class SpringBootRxjavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRxjavaApplication.class, args);
	}
	
}
