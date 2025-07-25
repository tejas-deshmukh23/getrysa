package com.lsp.web.ONDCService;

import java.util.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lsp.web.entity.Callback;
import com.lsp.web.repository.CallbackRepository;

import ondc.onboarding.utility.Utils;

@Service
public class UpdateService {
	
	@Autowired
	private CallbackRepository callbackRepository;
	
	private final RestTemplate restTemplate;
    public UpdateService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String bapUri = "https://uat.credithaat.in/";
    private final String bapId = "uat.credithaat.in";
    private final String version = "2.0.1";
    private final String domain = "ONDC:FIS12";
    private final String countryCode = "IND";
    private final String cityCode = "*";

    private static final String BASE64_PRIVATE_KEY = "DwIj0AObeY+06UHJoYrOYgoP9jz/9PGZilw4tqW9Jeg=";
    private static final String SUBSCRIBER_ID = "uat.credithaat.in";
    private static final String UNIQUE_KEY_ID = "642129cd-7856-4975-8496-d62cecd912b0";

	
	public ResponseEntity<?> update(
	        String transactionId,
	        String bppId,
	        String bppUri,
	        String orderId,
	        String fulfillmentState
	    ) {
	        String gatewayUrl = bppUri + "/update";

	        try {
	            String messageId = UUID.randomUUID().toString();
	            String timestamp = ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT);

	            // Context
	            Map<String, Object> context = new LinkedHashMap<>();
	            context.put("domain", domain);
	            context.put("location", Map.of(
	                "country", Map.of("code", countryCode),
	                "city", Map.of("code", cityCode)
	            ));
	            context.put("transaction_id", transactionId);
	            context.put("message_id", messageId);
	            context.put("action", "update");
	            context.put("timestamp", timestamp);
	            context.put("version", version);
	            context.put("bap_uri", bapUri);
	            context.put("bap_id", bapId);
	            context.put("ttl", "PT10M");
	            context.put("bpp_id", bppId);
	            context.put("bpp_uri", bppUri);

	            // Message
	            Map<String, Object> message = Map.of(
	                "update_target", "fulfillment",
	                "order", Map.of(
	                    "id", orderId,
	                    "fulfillments", List.of(
	                        Map.of("state", Map.of(
	                            "descriptor", Map.of("code", fulfillmentState)
	                        ))
	                    )
	                )
	            );

	            Map<String, Object> requestBody = new LinkedHashMap<>();
	            requestBody.put("context", context);
	            requestBody.put("message", message);

	            // Sign and send request
	            long created = System.currentTimeMillis() / 1000L;
	            long expires = created + 300;
	            String hashedReq = Utils.hashMassage(objectMapper.writeValueAsString(requestBody), created, expires);
	            String signature = Utils.sign(Base64.getDecoder().decode(BASE64_PRIVATE_KEY), hashedReq.getBytes());

	            String authorizationHeader = "Signature keyId=\"" + SUBSCRIBER_ID + "|" + UNIQUE_KEY_ID + "|ed25519\"," +
	                "algorithm=\"ed25519\"," +
	                "created=\"" + created + "\"," +
	                "expires=\"" + expires + "\"," +
	                "headers=\"(created) (expires) digest\"," +
	                "signature=\"" + signature + "\"";

	            HttpHeaders headers = new HttpHeaders();
	            headers.setContentType(MediaType.APPLICATION_JSON);
	            headers.setAccept(List.of(MediaType.APPLICATION_JSON));
	            headers.set("Authorization", authorizationHeader);

	            HttpEntity<String> entity = new HttpEntity<>(objectMapper.writeValueAsString(requestBody), headers);

	            ResponseEntity<String> response = restTemplate.postForEntity(gatewayUrl, entity, String.class);

	            Map<String, Object> responseMap = new LinkedHashMap<>();
	            responseMap.put("transaction_id", transactionId);
	            responseMap.put("message_id", messageId);
	            responseMap.put("gateway_response", objectMapper.readValue(response.getBody(), Object.class));

	            return ResponseEntity.ok(responseMap);

	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body(Map.of("error", "Failed to send update", "details", e.getMessage()));
	        }
	    }
	
	
	//on_update callback service
    public ResponseEntity<?> onUpdate(StringBuilder requestBody) throws JsonMappingException, JsonProcessingException{
    	
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
        callback.setApi("/on_update");
//        callback.setProduct("ONDC");
        
        callbackRepository.save(callback);
    	
    	
    	return null;
    	
    }

}
