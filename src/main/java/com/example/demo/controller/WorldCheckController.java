package com.example.demo.controller;

import com.example.demo.service.WorldCheckService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/worldCheck")
public class WorldCheckController {

    private final WorldCheckService worldCheckService;
    
    @Autowired
    public WorldCheckController(WorldCheckService worldCheckService) {
        this.worldCheckService = worldCheckService;
    }

    @GetMapping("{id}")
    public boolean getWorldCheck(@PathVariable Long id){
        return worldCheckService.getWorldCheck(id);
    }
    
}
