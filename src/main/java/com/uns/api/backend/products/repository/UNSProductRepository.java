package com.uns.api.backend.products.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.uns.api.backend.products.model.UNSProduct;

public interface UNSProductRepository extends MongoRepository<UNSProduct, String> {
    @Query("{name:'?0'}")
    UNSProduct findByName(String name);

    @Query("{sku:'?0'}")
    UNSProduct findBySku(String sku);

    public long count();
}
