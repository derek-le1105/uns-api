package com.uns.api.backend.shipments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.uns.api.backend.shipments.models.Shipments;
import com.uns.api.backend.shipments.models.Shipment;

import io.github.cdimascio.dotenv.Dotenv;

public class ShipstationService {
    public static String getShipstationURI(String date) {
        final String shipstationURI = "https://ssapi.shipstation.com/shipments?shipDateStart="
                + date + "&shipDateEnd="
                + date + "&pageSize=500&page=1";
        return shipstationURI;

    }

    public static String getShipstationURI(String date, Integer page) {
        final String shipstationURI = "https://ssapi.shipstation.com/shipments?shipDateStart="
                + date + "&shipDateEnd="
                + date + "&pageSize=500&page=" + page;
        return shipstationURI;
    }

    public static List<Shipment> getShipments(String date) {
        Dotenv dotenv = Dotenv.load();
        List<Shipment> shipmentList = new ArrayList<Shipment>();
        final String apiKey = dotenv.get("SHIPSTATION_KEY");
        final String apiSecret = dotenv.get("SHIPSTATION_SECRET");
        final String apiPartner = dotenv.get("SHIPSTATION_X_PARTNER_KEY");

        HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();
        String uri = getShipstationURI(date);
        // System.out.println(uri);
        try {
            String encodedAuth = Base64.getEncoder().encodeToString((apiKey + ":" + apiSecret).getBytes());
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.set("Authorization", "Basic " + encodedAuth);
            headers.set("x-partner", apiPartner);
            HttpEntity<Shipments> entity = new HttpEntity<Shipments>(headers);
            ResponseEntity<Shipments> response = restTemplate.exchange(uri, HttpMethod.GET, entity, Shipments.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                Shipments shipments = response.getBody();
                shipmentList.addAll(shipments.getShipments());

                if (shipments.getPages() > 1) {
                    for (int i = 2; i <= shipments.getPages(); i++) {
                        uri = getShipstationURI(date, i);
                        response = restTemplate.exchange(uri, HttpMethod.GET, entity, Shipments.class);
                        if (response.getStatusCode().is2xxSuccessful()) {
                            shipments = response.getBody();
                            shipmentList.addAll(shipments.getShipments());
                        }
                    }
                }
                return shipmentList;
            } else {
                // Handle non-successful status codes
                throw new HttpClientErrorException(response.getStatusCode());
            }
        } catch (HttpClientErrorException e) {
            // Handle HTTP errors
            e.printStackTrace();
        }
        return null;
    }
}
