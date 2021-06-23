package com.example.demo.service;

import java.util.Optional;

import com.example.demo.entities.WorldCheck;
import com.example.demo.repositories.WorldCheckRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorldCheckService {
    
    private final WorldCheckRepository worldCheckRepository;


    @Autowired
    public WorldCheckService(WorldCheckRepository worldCheckRepository) {
        this.worldCheckRepository = worldCheckRepository;
    }

    public boolean getWorldCheck(Long id) {

        Optional<WorldCheck> world = worldCheckRepository.findByCustomerId(id);
        if(!world.isPresent()){
            throw new IllegalStateException("No Worldcheck available");
        }
        return world.get().getCheck();
    }
}
