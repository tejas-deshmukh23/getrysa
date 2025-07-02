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

//import com.lsp.web.controller.ObjectMapper;
//import com.lsp.web.controller.RestTemplate;

@Service
public class SearchService {
	
	private final RestTemplate restTemplate;
	public SearchService(RestTemplate restTemplate) {
	    this.restTemplate = restTemplate;
	}
    private final ObjectMapper objectMapper = new ObjectMapper();
    private String bapUri = "https://uat.credithaat.in/";
    private String bapId = "uat.credithaat.in";
    private String version = "2.0.1";
    private String domain = "ONDC:FIS12";
    private String countryCode = "IND";
    private String cityCode = "*";
    private String gatewayUrl = "https://staging.gateway.proteantech.in/search";
    
    //This variables are needed to create signature-------------------------------
    private static final String BASE64_PRIVATE_KEY = "DwIj0AObeY+06UHJoYrOYgoP9jz/9PGZilw4tqW9Jeg=";
    private static final String SUBSCRIBER_ID = "uat.credithaat.in";
    private static final String UNIQUE_KEY_ID = "642129cd-7856-4975-8496-d62cecd912b0";

//    private static final String CREATED_ISO = "2025-06-19T11:12:50.788Z";
//    private static final String EXPIRES_ISO = "2026-10-20T18:00:15.071Z";
    //-----------------------------------------------------------------------------
    
    public ResponseEntity<?> search() {
        try {
            String transactionId = UUID.randomUUID().toString();
            String messageId = UUID.randomUUID().toString();
            String timestamp = ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT);

            Map<String, Object> requestBody = new LinkedHashMap<>();

            Map<String, Object> context = new LinkedHashMap<>();
            context.put("domain", domain);
            context.put("location", Map.of(
                    "country", Map.of("code", countryCode),
                    "city", Map.of("code", cityCode)
            ));
            context.put("transaction_id", transactionId);
            context.put("message_id", messageId);
            context.put("action", "search");
            context.put("timestamp", timestamp);
            context.put("version", version);
            context.put("bap_uri", bapUri);
            context.put("bap_id", bapId);
            context.put("ttl", "PT10M");

            Map<String, Object> buyerFinderFees = Map.of(
                    "descriptor", Map.of("code", "BUYER_FINDER_FEES"),
                    "display", false,
                    "list", List.of(
                            Map.of("descriptor", Map.of("code", "BUYER_FINDER_FEES_TYPE"), "value", "percent-annualized"),
                            Map.of("descriptor", Map.of("code", "BUYER_FINDER_FEES_PERCENTAGE"), "value", "1")
                    )
            );

            Map<String, Object> settlementTerms = Map.of(
                    "descriptor", Map.of("code", "SETTLEMENT_TERMS"),
                    "display", false,
                    "list", List.of(
                            Map.of("descriptor", Map.of("code", "DELAY_INTEREST"), "value", "2.5"),
                            Map.of("descriptor", Map.of("code", "STATIC_TERMS"), "value", "https://bap.credit.becknprotocol.io/personal-banking/loans/personal-loan"),
                            Map.of("descriptor", Map.of("code", "OFFLINE_CONTRACT"), "value", "true")
                    )
            );

            Map<String, Object> message = Map.of(
                    "intent", Map.of(
                            "category", Map.of("descriptor", Map.of("code", "PERSONAL_LOAN")),
                            "payment", Map.of(
                                    "collected_by", "BPP",
                                    "tags", List.of(buyerFinderFees, settlementTerms)
                            )
                    )
            );

            requestBody.put("context", context);
            requestBody.put("message", message);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(List.of(MediaType.APPLICATION_JSON));
            
            //here i will call the function to create Authorization header
            
            long created = System.currentTimeMillis() / 1000L;
            long expires = created + 300000;
//            logger.info(toBase64(generateBlakeHash(req.get("value").toString())));
//            logger.info(req.get("value").toString());
            String hashedReq = Utils.hashMassage(objectMapper.writeValueAsString(requestBody),created,expires);
            String signature = Utils.sign(Base64.getDecoder().decode(BASE64_PRIVATE_KEY),hashedReq.getBytes());
            String subscriberId = SUBSCRIBER_ID;
            String uniqueKeyId = UNIQUE_KEY_ID;

            String authorizationSignature = "Signature keyId=\"" + subscriberId + "|" + uniqueKeyId + "|" + "ed25519\"" + ",algorithm=\"ed25519\"," + "created=\"" + created + "\",expires=\"" + expires + "\",headers=\"(created) (expires)" + " digest\",signature=\"" + signature + "\"";
        

            // TODO: Add your signed Authorization header here
            headers.set("Authorization", authorizationSignature);

            HttpEntity<String> request = new HttpEntity<>(objectMapper.writeValueAsString(requestBody), headers);

            ResponseEntity<String> gatewayResponse = restTemplate.postForEntity(gatewayUrl, request, String.class);

            Map<String, Object> responseMap = new LinkedHashMap<>();
            responseMap.put("transaction_id", transactionId);
            responseMap.put("message_id", messageId);
            responseMap.put("timestamp", timestamp);
            responseMap.put("gateway_response", objectMapper.readValue(gatewayResponse.getBody(), Object.class));

            return ResponseEntity.ok(responseMap);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Failed to send ONDC search", "details", e.getMessage()));
        }
    }

}
