package com.lsp.web.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lsp.web.ONDCService.InitService;
import com.lsp.web.ONDCService.SearchService;
import com.lsp.web.ONDCService.SelectService;
import com.lsp.web.dto.InitRequestDTO;
import com.lsp.web.dto.SelectRequestDTO;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class ONDCController {
	
	@Autowired
	private SearchService searchService;
	@Autowired
	private SelectService selectService;
	@Autowired
	private InitService initService;
	
	@GetMapping("/search")
    public ResponseEntity<?> triggerSearch() {
        return searchService.search();
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
				selectRequestDTO.getStatus());
	}
	
	@PostMapping
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
                request.getSettlementAmount()
        );
    }

}
