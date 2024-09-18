// src/main/java/com/example/coingecko/CoinGeckoApplication.java
package com.example.coingecko;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CoingeckoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoingeckoApplication.class, args);
	}

	// Define RestTemplate Bean
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
