package com.uns.api.backend.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String id) {
        super("Could not find product " + id);
    }

}
