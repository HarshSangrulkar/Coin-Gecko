// src/main/java/com/example/coingecko/controller/CoinGeckoController.java
package com.example.coingecko.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.coingecko.model.CoinGeckoCoin;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CoinGeckoController {

    @Autowired
    private RestTemplate restTemplate;

    private static final String API_KEY = "API_KEY";
    private static final String API_BASE_URL = "https://api.coingecko.com/api/v3";

    @GetMapping("/api/coingecko/coins")
    public ResponseEntity<?> getTop30Coins() {
        String url = API_BASE_URL + "/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=30&page=1";

        // Set up the headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-cg-demo-api-key", API_KEY);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Make the API request
        ResponseEntity<CoinGeckoCoin[]> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                CoinGeckoCoin[].class);

        // Fetch the top 30 coins (already limited to 30 by the API query parameter)
        List<CoinGeckoCoin> topCoins = Arrays.stream(response.getBody())
                .collect(Collectors.toList());

        return ResponseEntity.ok(topCoins);
    }
}
