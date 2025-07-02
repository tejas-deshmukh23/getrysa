package com.lsp.web.ONDCService;

import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.databind.ObjectMapper;

import ondc.onboarding.utility.Utils;

@Service
public class SelectService {
	
	private final RestTemplate restTemplate;
	public SelectService(RestTemplate restTemplate) {
	    this.restTemplate = restTemplate;
	}
	 private final ObjectMapper objectMapper = new ObjectMapper();
	    private String bapUri = "https://uat.credithaat.in/";
	    private String bapId = "uat.credithaat.in";
	    private String version = "2.0.1";
	    private String domain = "ONDC:FIS12";
	    private String countryCode = "IND";
	    private String cityCode = "*";
	    
	    //This variables are needed to create signature-------------------------------
	    private static final String BASE64_PRIVATE_KEY = "DwIj0AObeY+06UHJoYrOYgoP9jz/9PGZilw4tqW9Jeg=";
	    private static final String SUBSCRIBER_ID = "uat.credithaat.in";
	    private static final String UNIQUE_KEY_ID = "642129cd-7856-4975-8496-d62cecd912b0";

	public ResponseEntity<?> select(String transactionId, String bppId, String bppUri, String providerId, String itemId,
			String formId, String submissionId, String status){
		
		String gatewayUrl = bppUri+"/select";
		
		try {
			
			
			
            String messageId = UUID.randomUUID().toString();
            String timestamp = ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT);

            // ---------- Context ----------
            Map<String, Object> context = new LinkedHashMap<>();
            context.put("domain", domain);
            context.put("location", Map.of(
                    "country", Map.of("code", countryCode),
                    "city", Map.of("code", cityCode)
            ));
            context.put("transaction_id", transactionId);
            context.put("message_id", messageId);
            context.put("action", "select");
            context.put("timestamp", timestamp);
            context.put("version", version);
            context.put("bap_uri", bapUri);
            context.put("bap_id", bapId);
            context.put("ttl", "PT10M");
            context.put("bpp_id", bppId);
            context.put("bpp_uri", bppUri);

            // ---------- Message ----------
            Map<String, Object> message = Map.of(
                    "order", Map.of(
                            "provider", Map.of("id", providerId),
                            "items", List.of(
                                    Map.of(
                                            "id", itemId,
                                            "xinput", Map.of(
                                                    "form", Map.of("id", formId),
                                                    "form_response", Map.of(
                                                            "status", "SUCCESS",
                                                            "submission_id", submissionId
                                                    )
                                            )
                                    )
                            )
                    )
            );

            Map<String, Object> requestBody = new LinkedHashMap<>();
            requestBody.put("context", context);
            requestBody.put("message", message);
            
            //code to create authorization signature
            long created = System.currentTimeMillis() / 1000L;
            long expires = created + 300000;
//            logger.info(toBase64(generateBlakeHash(req.get("value").toString())));
//            logger.info(req.get("value").toString());
            String hashedReq = Utils.hashMassage(objectMapper.writeValueAsString(requestBody),created,expires);
            String signature = Utils.sign(Base64.getDecoder().decode(BASE64_PRIVATE_KEY),hashedReq.getBytes());
            String subscriberId = SUBSCRIBER_ID;
            String uniqueKeyId = UNIQUE_KEY_ID;

            String authorizationSignature = "Signature keyId=\"" + subscriberId + "|" + uniqueKeyId + "|" + "ed25519\"" + ",algorithm=\"ed25519\"," + "created=\"" + created + "\",expires=\"" + expires + "\",headers=\"(created) (expires)" + " digest\",signature=\"" + signature + "\"";
        


            // ---------- HTTP Request ----------
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(List.of(MediaType.APPLICATION_JSON));
            headers.set("Authorization", authorizationSignature);

            HttpEntity<String> entity = new HttpEntity<>(objectMapper.writeValueAsString(requestBody), headers);

            ResponseEntity<String> response = restTemplate.postForEntity(gatewayUrl, entity, String.class);

            // ---------- Response Map ----------
            Map<String, Object> responseMap = new LinkedHashMap<>();
            responseMap.put("transaction_id", transactionId);
            responseMap.put("message_id", messageId);
            responseMap.put("gateway_response", objectMapper.readValue(response.getBody(), Object.class));

            return ResponseEntity.ok(responseMap);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Failed to send select", "details", e.getMessage()));
        }
    }

}


