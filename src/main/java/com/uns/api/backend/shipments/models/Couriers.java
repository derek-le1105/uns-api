package com.uns.api.backend.shipments.models;

import java.util.List;

public class Couriers {
    private List<Shipment> fedex;
    private List<Shipment> usps;
    private List<Shipment> gls;

    public Couriers(List<Shipment> fedex, List<Shipment> usps, List<Shipment> gls) {
        this.fedex = fedex;
        this.usps = usps;
        this.gls = gls;
    }

    // Getters
    public List<Shipment> getFedex() {
        return fedex;
    }

    public List<Shipment> getUsps() {
        return usps;
    }

    public List<Shipment> getGls() {
        return gls;
    }

    // Setters

    public void setFedex(List<Shipment> fedex) {
        this.fedex = fedex;
    }

    public void setUsps(List<Shipment> usps) {
        this.usps = usps;
    }

    public void setGls(List<Shipment> gls) {
        this.gls = gls;
    }
}
