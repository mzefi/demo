package com.example.demo.controller;

import java.text.ParseException;
import java.util.List;

import com.example.demo.entities.Customer;
import com.example.demo.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path= "/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getCustomers(){
        return customerService.getCustomers();
    }

    @PostMapping
    public void newCustomer(@RequestBody Customer customer){
        customerService.addNewCustomer(customer);
    }

    @PutMapping(path = "{id}")
    public void updateCustomer(
        @PathVariable("id") Long id, 
        @RequestParam(required = false) String name, 
        @RequestParam(required = false) String surname, 
        @RequestParam(required = false) String nationality, 
        @RequestParam(required = false) String birthDate) throws ParseException{

            customerService.updateCustomer(id, name, surname, nationality, birthDate);

    }

}