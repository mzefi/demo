package com.example.demo.repositories;

import java.util.Optional;

import com.example.demo.entities.WorldCheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Qualifier("worldCheck")
@Repository
public interface WorldCheckRepository extends JpaRepository<WorldCheck,Long>{

    public Optional<WorldCheck> findByCustomerId(Long id);

}
