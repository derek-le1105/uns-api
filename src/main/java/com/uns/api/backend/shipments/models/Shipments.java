package com.uns.api.backend.shipments.models;

import java.util.List;

public class Shipments {
    private List<Shipment> shipments;
    private Integer total;
    private Integer page;
    private Integer pages;

    public Shipments(List<Shipment> shipments, Integer total, Integer page, Integer pages) {
        this.shipments = shipments;
        this.total = total;
        this.page = page;
        this.pages = pages;
    }

    // Getters
    public List<Shipment> getShipments() {
        return shipments;
    }

    public Integer getTotal() {
        return total;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getPages() {
        return pages;
    }

    // Setters
    public void setShipments(List<Shipment> shipments) {
        this.shipments = shipments;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

}
