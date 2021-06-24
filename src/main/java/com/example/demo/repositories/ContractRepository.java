package com.example.demo.repositories;

import java.util.List;

import com.example.demo.entities.Contract;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepository extends JpaRepository<Contract,Long>{
    public List<Contract> findByContractNr(Long contractNr);
}
