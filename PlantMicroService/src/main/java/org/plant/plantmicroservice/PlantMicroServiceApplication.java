package org.plant.plantmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "org.plant")
@EnableJpaRepositories(basePackages = "org.plant.dao")
@EntityScan("org.plant.entity")
public class PlantMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlantMicroServiceApplication.class, args);
	}

}
