package com.lsp.web.controller;

//import java.io.BufferedReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.Map;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.lsp.web.ONDCService.ConfirmService;
import com.lsp.web.ONDCService.InitService;
import com.lsp.web.ONDCService.SearchService;
import com.lsp.web.ONDCService.SelectService;
import com.lsp.web.ONDCService.UpdateService;
import com.lsp.web.dto.InitRequestDTO;
import com.lsp.web.dto.SearchRequestDTO;
import com.lsp.web.dto.SelectRequestDTO;
import com.lsp.web.dto.UpdateRequestDTO;


import ondc.onboarding.utility.Routes;
import ondc.onboarding.utility.Utils;
///////////////////////



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@CrossOrigin("*")
public class ONDCController extends Utils {
	
	@Autowired
	private SearchService searchService;
	@Autowired
	private SelectService selectService;
	@Autowired
	private InitService initService;
	@Autowired
	private ConfirmService confirmService;
	@Autowired
	private UpdateService updateService;
	
	@GetMapping("/createTransactionId")
	public ResponseEntity<?> createId() {
		try {
			String transactionId = UUID.randomUUID().toString();
			return ResponseEntity.ok(transactionId);
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Failed to create transactionId", "details", e.getMessage()));
		}
	}
	
	//here we are taking the mobileNumber just to get UserInfo and save it in journeyLog its not related to ONDC's search api 
	@PostMapping("/search")
//	@RequestBody
    public ResponseEntity<?> triggerSearch(@RequestBody SearchRequestDTO searchRequestDTO) {
        return searchService.search(searchRequestDTO.getTransactionId(), searchRequestDTO.getMobileNumber(), searchRequestDTO.getStage());
    }
	
	
	@PostMapping("/select")
	public ResponseEntity<?> triggerSelect(@RequestBody SelectRequestDTO selectRequestDTO){
		return selectService.select(selectRequestDTO.getTransactionId(),
				selectRequestDTO.getBppId(),
				selectRequestDTO.getBppUri(),
				selectRequestDTO.getProviderId(),
				selectRequestDTO.getItemId(),
				selectRequestDTO.getFormId(),
				selectRequestDTO.getSubmissionId(),
				selectRequestDTO.getStatus(),
				selectRequestDTO.getMobileNumber(),
				selectRequestDTO.getStage(),
				selectRequestDTO.getProductName(),
				selectRequestDTO.getLoanAmount());
	}
	
	@PostMapping("/init")
    public ResponseEntity<?> callInit(@RequestBody InitRequestDTO request) {
        return initService.init(
                request.getTransactionId(),
                request.getBppId(),
                request.getBppUri(),
                request.getProviderId(),
                request.getItemId(),
                request.getFormId(),
                request.getSubmissionId(),
                request.getBankCode(),
                request.getAccountNumber(),
                request.getVpa(),
                request.getSettlementAmount(),
                request.getMobileNumber(),
                request.getStage(),
                request.getProductName(),
                request.getFormType(),
                request.getAccountname(),
                request.getAccountType(),
                request.getIFSC()
        );
    }
	
	@PostMapping("/confirm")
	public ResponseEntity<?> selectRequest(@RequestBody InitRequestDTO request) {
	    return confirmService.confirm(
	        request.getTransactionId(),
	        request.getBppId(),
	        request.getBppUri(),
	        request.getProviderId(),
	        request.getItemId(),
	        request.getFormId(),
	        request.getSubmissionId(),
	        request.getBankCode(),
	        request.getAccountNumber(),
	        request.getVpa(),
	        request.getSettlementAmount()
	    );
	}
	
	@PostMapping("/update")
    public ResponseEntity<?> updateRequest(@RequestBody UpdateRequestDTO request) {
        return updateService.update(
            request.getTransactionId(),
            request.getBppId(),
            request.getBppUri(),
            request.getOrderId(),
            request.getFulfillmentState()
        );
    }

}