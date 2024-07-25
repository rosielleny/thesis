package org.player.playermicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "org.player")
@EnableJpaRepositories(basePackages = "org.player.dao")
@EntityScan("org.player.entity")
public class PlayerMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlayerMicroserviceApplication.class, args);
    }

}
