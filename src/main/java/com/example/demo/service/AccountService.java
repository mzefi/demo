package com.example.demo.service;

import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import com.example.demo.entities.Account;
import com.example.demo.repositories.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    
    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void addAccount(Account account) {
        accountRepository.save(account);
    }

    @Transactional
    public void updateBlock(Long id, boolean block) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new IllegalStateException("Customer not found"));

        if(!Objects.equals(account.getBlocked(), block)){
            account.setBlocked(block);
        }
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    public List<Account> getAccount() {
        return accountRepository.findAll();
    }
    
}
