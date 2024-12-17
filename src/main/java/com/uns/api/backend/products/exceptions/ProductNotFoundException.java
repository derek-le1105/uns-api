package com.uns.api.backend.products.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String id) {
        super("Could not find product " + id);
    }

}
