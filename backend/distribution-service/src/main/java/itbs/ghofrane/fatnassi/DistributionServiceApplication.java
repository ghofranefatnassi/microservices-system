package itbs.ghofrane.fatnassi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients

public class DistributionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DistributionServiceApplication.class, args);
	}

}
