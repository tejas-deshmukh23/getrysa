package com.lsp.web.controller;

import java.io.BufferedReader;
import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class ONDCCallbackController {
	
	@RequestMapping(value = "/on_search", method = {RequestMethod.GET, RequestMethod.POST})
    public void handleCallback(HttpServletRequest request) throws IOException {
        StringBuilder requestBody = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;

        while ((line = reader.readLine()) != null) {
            requestBody.append(line);
        }

        System.out.println("The on_search callback received is: " + requestBody.toString());
        //here we will write code to save this callback in db
    }
	
	@RequestMapping(value = "/on_select", method = {RequestMethod.GET, RequestMethod.POST})
    public void handleOnSelectCallback(HttpServletRequest request) throws IOException {
        StringBuilder requestBody = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;

        while ((line = reader.readLine()) != null) {
            requestBody.append(line);
        }

        System.out.println("The onselect callback received is: " + requestBody.toString());
        //here we will write the code to save the callback in db
    }
	
	@RequestMapping(value = "/on_init", method = {RequestMethod.GET, RequestMethod.POST})
    public void handleOnInitCallback(HttpServletRequest request) throws IOException {
        StringBuilder requestBody = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;

        while ((line = reader.readLine()) != null) {
            requestBody.append(line);
        }

        System.out.println("The on_init callback received is: " + requestBody.toString());
        
        //here we will write code to save callback to db
    }
	
	@RequestMapping(value = "/on_confirm", method = {RequestMethod.GET, RequestMethod.POST})
    public void handleOnConfirmCallback(HttpServletRequest request) throws IOException {
        StringBuilder requestBody = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;

        while ((line = reader.readLine()) != null) {
            requestBody.append(line);
        }

        System.out.println("The onselect callback received is: " + requestBody.toString());
        //here we will write code to save callback to db
    }
	@RequestMapping(value = "/on_update", method = {RequestMethod.GET, RequestMethod.POST})
    public void handleOnUpdateCallback(HttpServletRequest request) throws IOException {
        StringBuilder requestBody = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;

        while ((line = reader.readLine()) != null) {
            requestBody.append(line);
        }

        System.out.println("The on_update callback received is: " + requestBody.toString());
        //here we will write code to save callback to db
    }
	
	@RequestMapping(value = "/on_status", method = {RequestMethod.GET, RequestMethod.POST})
    public void handleOnStatusCallback(HttpServletRequest request) throws IOException {
        StringBuilder requestBody = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;

        while ((line = reader.readLine()) != null) {
            requestBody.append(line);
        }

        System.out.println("The on_status callback received is: " + requestBody.toString());
        //here we will write code to save callback to db
    }

}
