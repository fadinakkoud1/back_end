package com.example.bookkeepingking.controller;

import com.example.bookkeepingking.model.Employee;
import com.example.bookkeepingking.service.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BooksController {

    @Autowired
    TaxService taxService;

    @GetMapping(value = "/")
    public String getHome() {
        return "Home Page";
    }

    @PostMapping(value = "/fetchTotalTax")
    @ResponseBody
    public Employee fetchTaxDetails(@RequestBody Employee employee) {
        return taxService.calculateTotalTax(employee);
    }

    @PostMapping(value = "/fetchSIT")
    @ResponseBody
    //change the return type to just sit
    public Employee fetchSIT(@RequestBody Employee employee) {
        return taxService.calculateStateDeductions(employee);
    }

}
