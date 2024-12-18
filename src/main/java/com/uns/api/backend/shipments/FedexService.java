package com.uns.api.backend.shipments;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uns.api.backend.shipments.models.Fedex;
import com.uns.api.backend.shipments.models.Shipment;
import io.github.cdimascio.dotenv.Dotenv;

public class FedexService {
    public static List<Shipment> getFedexShipments(List<Shipment> shipments) {
        try {
            System.out.println(shipments.size());
            String bearerToken = getAuth();
            List<String> batches = createTrackingBatches(shipments);
            List<Shipment> stuckshipments = getTrackingInfo(bearerToken, batches, shipments);
            return stuckshipments;
            // System.out.println(batches);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            // TODO: handle exception
        }
        return null;

    }

    private static String getAuth() {
        Dotenv dotenv = Dotenv.load();
        String FEDEX_API_KEY = dotenv.get("FEDEX_API_KEY");
        String FEDEX_SECRET_KEY = dotenv.get("FEDEX_SECRET_KEY");
        String fedexURL = "https://apis.fedex.com/oauth/token";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/x-www-form-urlencoded");

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("grant_type", "client_credentials");
        requestBody.add("client_id", FEDEX_API_KEY);
        requestBody.add("client_secret", FEDEX_SECRET_KEY);

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(fedexURL, HttpMethod.POST, request, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                JsonNode root = mapper.readTree(response.getBody());
                return root.path("access_token").asText();
            } catch (JsonMappingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (JsonProcessingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }

    private static List<String> createTrackingBatches(List<Shipment> shipments) throws JsonProcessingException {
        int batchSize = 30;
        int totalShipments = shipments.size();

        List<String> batches = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        for (int i = 0; i < (totalShipments + batchSize - 1) / batchSize; i++) {
            int start = i * batchSize;
            int end = Math.min(start + batchSize, totalShipments);
            List<TrackingInfo> trackingInfoList = new ArrayList<>();
            for (Shipment shipment : shipments.subList(start, end)) {
                trackingInfoList.add(new TrackingInfo(new TrackingNumberInfo(shipment.getTrackingNumber())));
            }
            TrackingBatch trackingBatch = new TrackingBatch(trackingInfoList);
            String batchJson = mapper.writeValueAsString(trackingBatch);
            batches.add(batchJson);
        }

        return batches;
    }

    private static List<Shipment> getTrackingInfo(String bearerToken, List<String> batches, List<Shipment> shipments)
            throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + bearerToken);
        headers.set("Content-Type", "application/json");

        RestTemplate restTemplate = new RestTemplate();
        String fedexURL = "https://apis.fedex.com/track/v1/trackingnumbers";

        ExecutorService executor = Executors.newFixedThreadPool(10); // Adjust the pool size as needed
        List<CompletableFuture<Void>> futures = new ArrayList<>();
        List<Shipment> stuckShipments = new ArrayList<>();
        for (String batch : batches) {
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                HttpEntity<String> request = new HttpEntity<>(batch, headers);
                try {
                    Fedex response = restTemplate.postForObject(fedexURL, request, Fedex.class);
                    if (response != null) {
                        List<Fedex.CompleteTrackResult> completeTrackResult = response.getOutput()
                                .getCompleteTrackResults();
                        for (Fedex.CompleteTrackResult trackResult : completeTrackResult) {

                            List<Fedex.TrackResult> trackResults = trackResult.getTrackResults();
                            for (Fedex.TrackResult result : trackResults) {

                                if (result.getError() != null) {
                                    System.err.println("Error in tracking result: " + result.getError().getMessage());
                                } else {
                                    String status = result.getLatestStatusDetail().getStatusByLocale();
                                    if (status.equals("Label created")) {
                                        stuckShipments.add(shipments.stream()
                                                .filter(s -> s.getTrackingNumber()
                                                        .equals(trackResult.getTrackingNumber()))
                                                .findFirst().get());
                                    }
                                }
                            }
                        }
                    } else {
                        System.err.println("Error: response is null");
                    }
                } catch (Exception e) {
                    System.err.println("Error: " + e.getMessage());
                }
            }, executor);
            futures.add(future);
        }

        // Wait for all futures to complete
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        // Shutdown the executor
        executor.shutdown();
        return stuckShipments;
    }

    private static class TrackingInfo {
        private TrackingNumberInfo trackingNumberInfo;

        public TrackingInfo(TrackingNumberInfo trackingNumberInfo) {
            this.trackingNumberInfo = trackingNumberInfo;
        }

        public TrackingNumberInfo getTrackingNumberInfo() {
            return trackingNumberInfo;
        }

        public void setTrackingNumberInfo(TrackingNumberInfo trackingNumberInfo) {
            this.trackingNumberInfo = trackingNumberInfo;
        }
    }

    private static class TrackingNumberInfo {
        private String trackingNumber;

        public TrackingNumberInfo(String trackingNumber) {
            this.trackingNumber = trackingNumber;
        }

        public String getTrackingNumber() {
            return trackingNumber;
        }

        public void setTrackingNumber(String trackingNumber) {
            this.trackingNumber = trackingNumber;
        }
    }

    private static class TrackingBatch {
        private List<TrackingInfo> trackingInfo;

        public TrackingBatch(List<TrackingInfo> trackingInfo) {
            this.trackingInfo = trackingInfo;
        }

        public List<TrackingInfo> getTrackingInfo() {
            return trackingInfo;
        }

        public void setTrackingInfo(List<TrackingInfo> trackingInfo) {
            this.trackingInfo = trackingInfo;
        }
    }
}
