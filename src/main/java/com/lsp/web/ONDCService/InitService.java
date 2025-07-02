package com.lsp.web.ONDCService;

import java.util.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import ondc.onboarding.utility.Utils;

@Service
public class InitService {

    private final RestTemplate restTemplate;
    public InitService(RestTemplate restTemplate) {
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

    public ResponseEntity<?> init(
        String transactionId,
        String bppId,
        String bppUri,
        String providerId,
        String itemId,
        String formId,
        String submissionId,
        String bankCode,
        String accountNumber,
        String vpa,
        String settlementAmount
    ) {
        String gatewayUrl = bppUri + "/init";

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
            context.put("action", "init");
            context.put("timestamp", timestamp);
            context.put("version", version);
            context.put("bap_uri", bapUri);
            context.put("bap_id", bapId);
            context.put("ttl", "PT10M");
            context.put("bpp_id", bppId);
            context.put("bpp_uri", bppUri);

            // Payments
            Map<String, Object> paymentParams = Map.of(
                "bank_code", bankCode,
                "bank_account_number", accountNumber,
                "virtual_payment_address", vpa
            );

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
                    Map.of("descriptor", Map.of("code", "SETTLEMENT_AMOUNT"), "value", settlementAmount),
                    Map.of("descriptor", Map.of("code", "SETTLEMENT_TYPE"), "value", "neft"),
                    Map.of("descriptor", Map.of("code", "DELAY_INTEREST"), "value", "5"),
                    Map.of("descriptor", Map.of("code", "STATIC_TERMS"), "value", "https://bap.credit.becknprotocol.io/personal-banking/loans/personal-loan"),
                    Map.of("descriptor", Map.of("code", "OFFLINE_CONTRACT"), "value", "true")
                )
            );

            // Message
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
                    ),
                    "payments", List.of(
                        Map.of(
                            "collected_by", "BPP",
                            "type", "ON_ORDER",
                            "status", "NOT-PAID",
                            "params", paymentParams,
                            "tags", List.of(buyerFinderFees, settlementTerms)
                        )
                    )
                )
            );

            Map<String, Object> requestBody = new LinkedHashMap<>();
            requestBody.put("context", context);
            requestBody.put("message", message);

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
                .body(Map.of("error", "Failed to send init", "details", e.getMessage()));
        }
    }
}