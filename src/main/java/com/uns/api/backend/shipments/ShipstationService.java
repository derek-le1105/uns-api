package com.uns.api.backend.shipments;

import org.springframework.stereotype.Service;

@Service
public class ShipstationService {
    public void getShipments(String date) {
        final String shipstationURI = "https://ssapi.shipstation.com/shipments?shipDateStart=%s&shipDateEnd=%s";
    }
}
