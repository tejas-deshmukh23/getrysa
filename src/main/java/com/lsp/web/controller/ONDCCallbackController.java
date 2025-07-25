package com.lsp.web.controller;

import java.io.BufferedReader;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lsp.web.ONDCService.ConfirmService;
import com.lsp.web.ONDCService.InitService;
import com.lsp.web.ONDCService.SearchService;
import com.lsp.web.ONDCService.SelectService;
import com.lsp.web.ONDCService.StatusService;
import com.lsp.web.ONDCService.UpdateService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class ONDCCallbackController {
	
	@Autowired
	private SearchService searchService;
	@Autowired
	private SelectService selectService;
	@Autowired
	private InitService initService;
	@Autowired
	private StatusService statusService;
	@Autowired
	private ConfirmService confirmService;
	@Autowired
	private UpdateService updateService;
	
	@RequestMapping(value = "/on_search", method = {RequestMethod.GET, RequestMethod.POST})
    public void handleCallback(HttpServletRequest request) throws IOException {
		
		try {
			StringBuilder requestBody = new StringBuilder();
	        BufferedReader reader = request.getReader();
	        String line;

	        while ((line = reader.readLine()) != null) {
	            requestBody.append(line);
	        }
			searchService.onSearch(requestBody);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
        

//        System.out.println("The on_search callback received is: " + requestBody.toString());
        //here we will write code to save this callback in db
        
    }
	
	@RequestMapping(value = "/on_select", method = {RequestMethod.GET, RequestMethod.POST})
    public void handleOnSelectCallback(HttpServletRequest request) throws IOException {
		try {
			StringBuilder requestBody = new StringBuilder();
	        BufferedReader reader = request.getReader();
	        String line;

	        while ((line = reader.readLine()) != null) {
	            requestBody.append(line);
	        }
			selectService.onSelect(requestBody);
		}catch(Exception e) {
			e.printStackTrace();
		}
    }
	
	@RequestMapping(value = "/on_init", method = {RequestMethod.GET, RequestMethod.POST})
    public void handleOnInitCallback(HttpServletRequest request) throws IOException {
		try {
			StringBuilder requestBody = new StringBuilder();
	        BufferedReader reader = request.getReader();
	        String line;

	        while ((line = reader.readLine()) != null) {
	            requestBody.append(line);
	        }
	        initService.onInit(requestBody);
		}catch(Exception e) {
			e.printStackTrace();
		}
    }
	
	@RequestMapping(value = "/on_confirm", method = {RequestMethod.GET, RequestMethod.POST})
    public void handleOnConfirmCallback(HttpServletRequest request) throws IOException {
		try {
			StringBuilder requestBody = new StringBuilder();
	        BufferedReader reader = request.getReader();
	        String line;

	        while ((line = reader.readLine()) != null) {
	            requestBody.append(line);
	        }
			confirmService.onConfirm(requestBody);
		}catch(Exception e) {
			e.printStackTrace();
		}
    }
	@RequestMapping(value = "/on_update", method = {RequestMethod.GET, RequestMethod.POST})
    public void handleOnUpdateCallback(HttpServletRequest request) throws IOException {
		try {
			StringBuilder requestBody = new StringBuilder();
	        BufferedReader reader = request.getReader();
	        String line;

	        while ((line = reader.readLine()) != null) {
	            requestBody.append(line);
	        }
			updateService.onUpdate(requestBody);
		}catch(Exception e) {
			e.printStackTrace();
		}

    }
	
	@RequestMapping(value = "/on_status", method = {RequestMethod.GET, RequestMethod.POST})
    public void handleOnStatusCallback(HttpServletRequest request) throws IOException {
		try {
			StringBuilder requestBody = new StringBuilder();
	        BufferedReader reader = request.getReader();
	        String line;

	        while ((line = reader.readLine()) != null) {
	            requestBody.append(line);
	        }
			statusService.onStatus(requestBody);
		}catch(Exception e) {
			e.printStackTrace();
		}
    }

}
