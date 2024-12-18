package com.uns.api.backend.shipments;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.uns.api.backend.util.DateUtils;

import com.uns.api.backend.shipments.models.Shipment;

@RestController
public class ShipmentsController {
    @GetMapping("/shipments")
    List<Shipment> get(@RequestParam(required = false) String date) {
        if (date == null)
            date = DateUtils.getCheckingDate();

        List<Shipment> shipments = ShipstationService.getShipments(date);
        System.out.println(shipments.size());
        return shipments;
    }
}
