package com.example.tp4_stock.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping("/home")
    public ModelAndView home() {
        ModelAndView model = new ModelAndView("/stock/home");
        model.addObject("stock", stockService.getStockList());
        return model;
    }

    @PostMapping("/restock")
    public ModelAndView restock() {
        stockService.restockAll();
        return home();
    }

    @PostMapping("/refresh")
    public ModelAndView refresh() {
        return home();
    }
}
