package com.uns.api.backend.shipments;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.uns.api.backend.util.DateUtils;
import com.uns.api.backend.shipments.models.Couriers;
import com.uns.api.backend.shipments.models.Shipment;

@RestController
public class ShipmentsController {

    @GetMapping("/shipments")
    List<Shipment> get(@RequestParam(required = false) String date) {
        if (date == null)
            date = DateUtils.getCheckingDate();

        List<Shipment> shipments = ShipstationService.getShipments(date);
        Couriers couriers = assignCouriers(shipments);
        FedexService.getFedexShipments(couriers.getFedex());
        return shipments;
    }

    public Couriers assignCouriers(List<Shipment> shipments) {
        List<Shipment> fedex = shipments.stream().filter(s -> s.getCarrierCode().equals("fedex")).toList();
        List<Shipment> usps = shipments.stream().filter(s -> s.getCarrierCode().equals("stamps_com")).toList();
        List<Shipment> gls = shipments.stream().filter(s -> s.getCarrierCode().equals("gls_us")).toList();

        return new Couriers(fedex, usps, gls);
    }
}
