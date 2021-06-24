package com.example.demo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Contract {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private Long contractNr;
    private boolean verified;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Account account;
    
    public Long getContractNr() {
        return contractNr;
    }
    public void setContractNr(Long contractNr) {
        this.contractNr = contractNr;
    }
    public boolean isVerified() {
        return verified;
    }
    public void setVerified(boolean visited) {
        this.verified = visited;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public Account getAccount() {
        return account;
    }
    public void setAccount(Account account) {
        this.account = account;
    }
}
