package com.example.demo.handler;


import com.example.demo.controller.ContractController;
import com.example.demo.entities.Contract;
import com.example.demo.googleactionsobjects.GoogleActionsCard;
import com.example.demo.googleactionsobjects.GoogleActionsContentCard;
import com.example.demo.googleactionsobjects.GoogleActionsPrompt;
import com.example.demo.googleactionsobjects.GoogleActionsRequest;
import com.example.demo.googleactionsobjects.GoogleActionsResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WebhookHandler {
    
    @Autowired
    private ContractController contractController;
    

    public GoogleActionsResponse handleGetContractData(GoogleActionsRequest request){
        String contractData = this.getContractData(request);

        GoogleActionsResponse response = GoogleActionsResponse.builder()
            .prompt(GoogleActionsPrompt.builder()
                .content(GoogleActionsContentCard.builder()
                    .card(GoogleActionsCard.builder()
                        .text(contractData)
                            .build())
                        .build())
                    .build())
                .session(request.getSession())
            .scene(request.getScene())
        .build();

        return response;
    }

    private String getContractData(GoogleActionsRequest request) {
        String contractData = "";
        String contractNr = request.getSession().getParams().get("contract_num").toString();
        
        if(contractNr.isEmpty()){
            contractData = "Bitte Antragsnummer angeben";
        }

        Contract contract = contractController.findContractById(Long.valueOf(contractNr));
        contractData = "Name: "+contract.getCustomer().getSurname()+" "+contract.getCustomer().getName()+"\nGeburtsdatum: "+contract.getCustomer().getBirthDate().toString()+"\nNationalität: "+contract.getCustomer().getNationality()+"\n";
        return contractData;
    }
}
