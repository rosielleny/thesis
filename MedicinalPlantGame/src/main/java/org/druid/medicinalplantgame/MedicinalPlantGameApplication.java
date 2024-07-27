package org.druid.medicinalplantgame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = "org.druid")
@EntityScan("com.druid.entity")
public class MedicinalPlantGameApplication {

    public static void main(String[] args) {

        SpringApplication.run(MedicinalPlantGameApplication.class, args);
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    HttpHeaders headers(){
        return new HttpHeaders();
    }
}
