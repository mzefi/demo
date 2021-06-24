package com.example.demo.controller;

import java.text.ParseException;
import java.util.List;

import com.example.demo.entities.Account;
import com.example.demo.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path= "/api/v1/account")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public List<Account> getAccounts(){
        return accountService.getAccount();
    }

    @PutMapping(path = "{id}")
    public void updateBlock(
        @PathVariable("id") Long id, 
        @RequestParam(required = false) boolean block) throws ParseException{

            accountService.updateBlock(id, block);

    }

    @DeleteMapping(path = "{id}")
    public void deleteAccount(Long id){
        accountService.deleteAccount(id);
    }
    
}
