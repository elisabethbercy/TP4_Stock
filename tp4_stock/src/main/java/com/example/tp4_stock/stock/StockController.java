package com.example.tp4_stock.stock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import ch.qos.logback.core.model.Model;


@Controller
@RequestMapping("/stock")
public class StockController {

    @Autowired 
    StockService st;

       
	
	@GetMapping("/home")
	public ModelAndView home() {
        // Create a list of Stock objects
        List<Stock> stock = new ArrayList<>();
    
        // Create Stock objects
        var a1 = new Stock("Table", 20);
        var a2 = new Stock("Bags", 22);
        var a3 = new Stock("Books", 10);
        var a4 = new Stock("Miks", 17);
    
        // Add the Stock objects to the list
        stock.add(a1);
        stock.add(a2);
        stock.add(a3);
        stock.add(a4);
    
        // Create the ModelAndView and add the list of stocks
        ModelAndView model = new ModelAndView("/stock/home");
        model.addObject("stock", stock);
    
        // Return the ModelAndView
        return model;
    }

    @PostMapping("/restock")
    public ModelAndView restock() {
        // Create a ModelAndView object for the restock page
       return home();

    }
    
    

    
}