package com.example.tp4_stock.stock;

public class Stock {

    private String name;
    private int qty;
    

   
    public Stock(String name, int qty) {
        this.qty = qty;
        this.name = name;
    }

    public Stock() {

    }

    public int getQty() {
        return qty;
    }
    public void setQty(int qty) {
        this.qty = qty;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
