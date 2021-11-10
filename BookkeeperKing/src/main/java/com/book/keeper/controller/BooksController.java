package com.book.keeper.controller;

import com.book.keeper.service.TaxService;
import com.book.keeper.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BooksController {

    @Autowired
    TaxService taxService;

    @GetMapping(value = "/fetchTotalTax")
    public Employee fetchTaxDetails(@RequestBody Employee employee) {
        return taxService.calculateTotalTax(employee);
    }

    @GetMapping(value = "/fetchSIT")
    public Employee fetchSIT(@RequestBody Employee employee) {
        return taxService.calculateStateDeductions(employee);
    }
}
