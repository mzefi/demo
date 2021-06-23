package com.example.demo.service;

import java.util.List;

import com.example.demo.entities.Contract;
import com.example.demo.repositories.ContractRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class ContractService {

    private final ContractRepository contractRepository;


    @Autowired
    public ContractService(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    public void addNewContract(Contract contract) {
        contractRepository.save(contract);
    }

    public List<Contract> getContract() {
        return contractRepository.findAll();
    }

}
