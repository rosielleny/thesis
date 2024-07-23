package org.game.gamemechanics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "org.game")
@EnableJpaRepositories(basePackages = "org.game.dao")
@EntityScan("org.game.entity")
public class GameMechanicsApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameMechanicsApplication.class, args);
	}

}
