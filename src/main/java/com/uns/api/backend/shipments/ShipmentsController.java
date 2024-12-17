package com.uns.api.backend.shipments;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.uns.api.backend.util.DateUtils;

@RestController
public class ShipmentsController {
    @GetMapping("/shipments")
    String getShipments(@RequestParam(required = false) String date) {
        if (date == null)
            date = DateUtils.getCheckingDate();

        return date;
    }
}
