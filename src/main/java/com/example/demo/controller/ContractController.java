package com.example.demo.controller;

import java.text.ParseException;
import java.util.List;

import com.example.demo.ContractModel;
import com.example.demo.entities.Contract;
import com.example.demo.service.ContractService;

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
@RequestMapping(path= "/api/v1/contract")
public class ContractController {
    private final ContractService contractService;
    
    @Autowired
    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    //POST, GET

    @GetMapping
    public List<Contract> getContract(){
        return contractService.getContract();
    }

    @PostMapping
    public void addNewContract(@RequestBody ContractModel contractModel) throws ParseException{
        contractService.addNewContract(contractModel);
    }

    @PutMapping(path = "{id}")
    public void setVerified(
        @PathVariable("id") Long contractNr, 
        @RequestParam(required = false) boolean verified) throws ParseException{

        contractService.setVerified(contractNr, verified);

    }


}
