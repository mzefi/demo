package com.example.demo.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import com.example.demo.ContractModel;
import com.example.demo.entities.Account;
import com.example.demo.entities.Contract;
import com.example.demo.entities.Customer;
import com.example.demo.repositories.AccountRepository;
import com.example.demo.repositories.ContractRepository;
import com.example.demo.repositories.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractService {

    private final ContractRepository contractRepository;
    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;


    @Autowired
    public ContractService(ContractRepository contractRepository, CustomerRepository customerRepository, AccountRepository accountRepository) {
        this.contractRepository = contractRepository;
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
    }

    public void addNewContract(ContractModel model) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");  
        Date birthDate = formatter.parse(model.getBirthDate());

        Customer customer = new Customer();
        customer.setName(model.getName());
        customer.setSurname(model.getSurname());
        customer.setNationality(model.getNationality());
        customer.setBirthDate(birthDate);
        customer.setAddress(model.getAddress());
        customer.setPostcode(model.getPostcode());
        customer.setTown(model.getTown());
        customer.setWorldCheck(true);
        customerRepository.save(customer);

        Account account = new Account();
        account.setBlocked(true);
        accountRepository.save(account);

        Contract contract = new Contract();
        contract.setContractNr(model.getContractNr());
        contract.setVerified(false);
        contract.setCustomer(customer);
        contract.setAccount(account);
        contractRepository.save(contract);
    }

    public List<Contract> getContract() {
        return contractRepository.findAll();
    }

    public Contract findContractById(Long contractId){

        List<Contract> contracts = contractRepository.findByContractNr(contractId);
        if(contracts.isEmpty()){
            throw new IllegalStateException("Antrag mit der ID: "+contractId+" nicht vorhanden");
        }
        
        Contract contract = contracts.get(0);

        return contract; 
    }

    @Transactional
    public void setVerified(Long contractNr, boolean verified) {
        List<Contract> contracts = contractRepository.findByContractNr(contractNr);
        if(contracts.isEmpty()){
            throw new IllegalStateException("Customer not found");
        }
        Contract contract = contracts.get(0);

        if(!Objects.equals(contract.isVerified(), verified)){
            contract.setVerified(verified);
        }
    }

}
