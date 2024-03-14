package com.ThankSens.Hentori;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HentoriApplication {
	public static void main(String[] args) {
		SpringApplication.run(HentoriApplication.class, args);
	}

}
