package com.example.tp4_stock.stock;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StockService {

    private List<Stock> stockList = new ArrayList<>();

  
    public StockService() {
        stockList.add(new Stock("Table", 20));
        stockList.add(new Stock("Bags", 22));
        stockList.add(new Stock("Books", 10));
        stockList.add(new Stock("Miks", 17));
    }

    public List<Stock> getAllStock() {
        return stockList;
    }


    public void updateStock(String name, int qty) {
        for (Stock stock : stockList) {
            if (stock.getName().equals(name)) {
                stock.setQty(stock.getQty() - qty);
                break;
            }
        }
    }

    public void restock() {
        // Restock to original
        for (Stock stock : stockList) {
            stock.setQty(20); 
            // reset stock to 20 for all items
        }
    }

    
    public void refreshStock() {
        
    }
}
