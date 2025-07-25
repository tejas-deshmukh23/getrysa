package com.lsp.web.ONDCService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lsp.web.entity.Callback;
import com.lsp.web.repository.CallbackRepository;

@Service
public class StatusService {
	
	@Autowired
    private SimpMessagingTemplate messagingTemplate;
	
	@Autowired
	private CallbackRepository callbackRepository;
	
	//code to store select callback
    public ResponseEntity<?> onStatus(StringBuilder requestBody) throws JsonMappingException, JsonProcessingException{
    	
    	// Parse JSON
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(requestBody.toString());

        String transactionId = jsonNode.path("context").path("transaction_id").asText();
        String messageId = jsonNode.path("context").path("message_id").asText();
 
        System.out.println("Transaction ID: " + transactionId);
        System.out.println("Message ID: " + messageId);
        
        Callback callback = new Callback();
        callback.setuID(transactionId);
        callback.setApiId(messageId);
        callback.setContent(requestBody.toString());
        callback.setApi("/on_status");
//        callback.setProduct("ONDC");
        
     // Broadcast to frontend subscribers
        messagingTemplate.convertAndSend("/topic/callbacks/status/"+transactionId, callback);
        
        callbackRepository.save(callback);
    	
    	
    	return null;
    	
    }

}
