package Reto4.despliegue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EntityScan("Reto4.despliegue.entitys")
public class R4Application {

	public static void main(String[] args) {
		SpringApplication.run(R4Application.class, args);
	}

}
