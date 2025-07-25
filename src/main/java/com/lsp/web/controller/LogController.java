package com.lsp.web.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.lsp.web.Exception.UserInfoNotFoundException;
import com.lsp.web.entity.JourneyLog;
import com.lsp.web.entity.Logger;
import com.lsp.web.entity.UserInfo;
import com.lsp.web.repository.UserInfoRepository;

@RestController
public class LogController {
	
//	public ResponseEntity<?> createJourneyLog(){
		//Load the userInfo to save the userInfo id into journeyLog
//        UserInfo userInfo;
//        Optional<UserInfo> optionalUserInfo = UserInfoRepository.findByMobileNumber(mobileNumber);
//        if(optionalUserInfo.isEmpty()) {
//        	throw new UserInfoNotFoundException("UserInfo not found with mobileNumber : "+mobileNumber);
//        	
//        }
//      //logic to save the journey log
//        JourneyLog journeyLog = new JourneyLog();
//        journeyLog.setPlatformId("O");
//        journeyLog.setRequestId(gatewayUrl);
//        journeyLog.setStage(stage);
//        journeyLog.setUId(transactionId);
//        journeyLog.setUser(userInfo);
//        journeyLogRepository.save(journeyLog);
//        
//        //here we will save this api call in logger
//        Logger logger = new Logger();
//        logger.setJourneyLog(journeyLog);
////        logger.setUrl(gatewayUrl);// this url doesnt refers the value of api url it holds the url if we get from response of that api
//        logger.setRequestPayload(String.valueOf(request));
//        logger.setResponsePayload(String.valueOf(responseMap));
//        loggerRepository.save(logger);
//	}

}
