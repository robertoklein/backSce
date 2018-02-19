package br.com.pti.lassesce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@EntityScan(basePackageClasses = {LasseSceApplication.class, Jsr310JpaConverters.class})

@SpringBootApplication
public class LasseSceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LasseSceApplication.class, args);
	}
}
