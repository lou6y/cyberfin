package tn.esprit.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class CyberFin2Application {

	public static void main(String[] args) {
		SpringApplication.run(CyberFin2Application.class, args);
	}

}
