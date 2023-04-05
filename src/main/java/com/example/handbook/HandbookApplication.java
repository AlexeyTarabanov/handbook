package com.example.handbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class HandbookApplication {

	public static void main(String[] args) {
		SpringApplication.run(HandbookApplication.class, args);
	}

}
