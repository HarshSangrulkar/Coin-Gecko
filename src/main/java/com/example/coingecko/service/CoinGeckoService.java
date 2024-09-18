package com.example.coingecko.service;

import com.example.coingecko.model.Coin;
import com.example.coingecko.model.MarketData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class CoinGeckoService {

    @Value("${coingecko.api.url}")
    private String apiUrl;

    @Value("${coingecko.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    // Ping Endpoint: To check if the API server is running
    public String ping() {
        String url = apiUrl + "/ping?x_cg_demo_api_key=" + apiKey;
        return restTemplate.getForObject(url, String.class);
    }

    // Get a list of all supported coins
    public List<Coin> getAllCoins() {
        String url = apiUrl + "/coins/list?x_cg_demo_api_key=" + apiKey;
        Coin[] coins = restTemplate.getForObject(url, Coin[].class);
        return Arrays.asList(coins);
    }

    // Get market data for a specific coin (e.g., bitcoin)
    public MarketData getCoinMarketData(String coinId) {
        String url = apiUrl + "/coins/markets?vs_currency=usd&ids=" + coinId + "&x_cg_demo_api_key=" + apiKey;
        MarketData[] marketData = restTemplate.getForObject(url, MarketData[].class);
        return marketData != null && marketData.length > 0 ? marketData[0] : null;
    }

    // Get supported currencies
    public List<String> getSupportedVsCurrencies() {
        String url = apiUrl + "/simple/supported_vs_currencies?x_cg_demo_api_key=" + apiKey;
        return Arrays.asList(restTemplate.getForObject(url, String[].class));
    }
}
