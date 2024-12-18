package com.uns.api.backend.shipments.models;

public class Shipment {
    private long shipmentId;
    private long orderId;
    private String orderKey;
    private String orderNumber;
    private String trackingNumber;
    private boolean isReturnLabel;
    private String batchNumber;
    private String carrierCode;
    private String serviceCode;
    private boolean voided;

    Shipment(long shipmentId, long orderId, String orderKey, String orderNumber, String trackingNumber,
            boolean isReturnLabel, String batchNumber, String carrierCode, String serviceCode, boolean voided) {
        this.shipmentId = shipmentId;
        this.orderId = orderId;
        this.orderKey = orderKey;
        this.orderNumber = orderNumber;
        this.trackingNumber = trackingNumber;
        this.isReturnLabel = isReturnLabel;
        this.batchNumber = batchNumber;
        this.carrierCode = carrierCode;
        this.serviceCode = serviceCode;
        this.voided = voided;
    }

    // Getters
    public long getShipmentId() {
        return shipmentId;
    }

    public long getOrderId() {
        return orderId;
    }

    public String getOrderKey() {
        return orderKey;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public boolean isReturnLabel() {
        return isReturnLabel;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public String getCarrierCode() {
        return carrierCode;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public boolean isVoided() {
        return voided;
    }

    // Setters

    public void setShipmentId(long shipmentId) {
        this.shipmentId = shipmentId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public void setOrderKey(String orderKey) {
        this.orderKey = orderKey;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public void setReturnLabel(boolean returnLabel) {
        isReturnLabel = returnLabel;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public void setCarrierCode(String carrierCode) {
        this.carrierCode = carrierCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public void setVoided(boolean voided) {
        this.voided = voided;
    }
}
