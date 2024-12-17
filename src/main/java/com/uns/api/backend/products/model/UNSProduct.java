package com.uns.api.backend.products.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
public class UNSProduct {
    @Id
    private String _id;
    private String sku;
    private String title;
    private String updated_at;
    private String updated_by;
    private String gtin_type;
    private Long gtin_id;
    private String brand_name;
    private String vendor_name;
    private String offered_markets;
    private String origin_country;
    private String tariff_code;

    public UNSProduct(String _id, String sku, String title, String updated_at, String updated_by, String gtin_type,
            Long gtin_id, String brand_name, String vendor_name, String offered_markets, String origin_country,
            String tariff_code) {
        super();
        this._id = _id;
        this.sku = sku;
        this.title = title;
        this.updated_at = updated_at;
        this.updated_by = updated_by;
        this.gtin_type = gtin_type;
        this.gtin_id = gtin_id;
        this.brand_name = brand_name;
        this.vendor_name = vendor_name;
        this.offered_markets = offered_markets;
        this.origin_country = origin_country;
        this.tariff_code = tariff_code;
    }

    // Getters
    public String getId() {
        return _id;
    }

    public String getTitle() {
        return title;
    }

    public String getSku() {
        return sku;
    }

    public String getUpdatedAt() {
        return updated_at;
    }

    public String getUpdatedBy() {
        return updated_by;
    }

    public String getGtinType() {
        return gtin_type;
    }

    public Long getGtinId() {
        return gtin_id;
    }

    public String getBrandName() {
        return brand_name;
    }

    public String getVendorName() {
        return vendor_name;
    }

    public String getOfferedMarkets() {
        return offered_markets;
    }

    public String getOriginCountry() {
        return origin_country;
    }

    public String getTariffCode() {
        return tariff_code;
    }

    // Setters

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public void setUpdatedAt(String updated_at) {
        this.updated_at = updated_at;
    }

    public void setUpdatedBy(String updated_by) {
        this.updated_by = updated_by;
    }

    public void setGtinType(String gtin_type) {
        this.gtin_type = gtin_type;
    }

    public void setGtinId(Long gtin_id) {
        this.gtin_id = gtin_id;
    }

    public void setBrandName(String brand_name) {
        this.brand_name = brand_name;
    }

    public void setVendorName(String vendor_name) {
        this.vendor_name = vendor_name;
    }

    public void setOfferedMarkets(String offered_markets) {
        this.offered_markets = offered_markets;
    }

    public void setOriginCountry(String origin_country) {
        this.origin_country = origin_country;
    }

    public void setTariffCode(String tariff_code) {
        this.tariff_code = tariff_code;
    }

}
