package com.example.coingecko.model;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class MarketData {
    private String id;
    private String symbol;
    private String name;
    private BigDecimal current_price;
    private BigDecimal market_cap;
    private BigDecimal total_volume;
}
