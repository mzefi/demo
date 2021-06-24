package com.example.demo.controller;

import java.text.ParseException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.example.demo.ContractModel;
import com.example.demo.entities.Contract;
import com.example.demo.googleactionsobjects.GoogleActionsPrompt;
import com.example.demo.googleactionsobjects.GoogleActionsRequest;
import com.example.demo.googleactionsobjects.GoogleActionsResponse;
import com.example.demo.googleactionsobjects.GoogleActionsSimple;
import com.example.demo.handler.WebhookHandler;
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
    private WebhookHandler webhookHandler;
    
    @Autowired
    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }


    //POST, GET

    @GetMapping
    public List<Contract> getContract(){
        return contractService.getContract();
    }

    public Contract findContractById(Long contractNr){
        return contractService.findContractById(contractNr);
    }

    @PostMapping(value = "/webhookhandler")
    public GoogleActionsResponse handleContractRequest(@RequestBody GoogleActionsRequest body) throws InterruptedException, ExecutionException {
        // Handler ermitteln
        String handlerName = body.getRequestJson().getHandler().getName();

        GoogleActionsResponse response = null;

        if (handlerName.equals("getContractData")) {
            response = webhookHandler.handleGetContractData(body);
        } else if (handlerName.equals("postCredentialData")) {
            //response = webhookHandler.;
        } else {
            // Response no handler found zusammenstellen
           
            response = GoogleActionsResponse.builder()
                .prompt(GoogleActionsPrompt.builder()
                        .firstSimple(GoogleActionsSimple.builder()
                                .speech("Es gibt keinen Handler für '" + handlerName + "'")
                                .build())
                        .build())
                .session(body.getRequestJson().getSession())
                .scene(body.getRequestJson().getScene())
                .build();
        }

        return response;
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
