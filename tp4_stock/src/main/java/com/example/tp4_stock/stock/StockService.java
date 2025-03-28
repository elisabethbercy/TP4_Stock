package com.example.tp4_stock.stock;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockService {

    private final Map<String, Integer> stockMap = new HashMap<>();

    public StockService() {
        // Initialize stock
        stockMap.put("Table", 20);
        stockMap.put("Bags", 22);
        stockMap.put("Books", 10);
        stockMap.put("Miks", 17);
    }

    // Subtract quantity when an item is sold or used
    public void updateStock(String name, int qty) {
        stockMap.computeIfPresent(name, (key, oldQty) -> Math.max(oldQty - qty, 0));
    }

    // Reset all stock to default values
    public void restockAll() {
        stockMap.put("Table", 20);
        stockMap.put("Bags", 22);
        stockMap.put("Books", 10);
        stockMap.put("Miks", 17);
    }

    // Return stock as a list of Stock objects
    public List<Stock> getStockList() {
        return stockMap.entrySet().stream()
                .map(entry -> new Stock(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }
}
