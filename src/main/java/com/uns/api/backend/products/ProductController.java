package com.uns.api.backend.products;

import org.springframework.web.bind.annotation.RestController;

import com.uns.api.backend.products.exceptions.ProductNotFoundException;
import com.uns.api.backend.products.model.UNSProduct;
import com.uns.api.backend.products.repository.UNSProductRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class ProductController {

    private final UNSProductRepository unsProductRepository;

    public ProductController(UNSProductRepository unsProductRepository) {
        this.unsProductRepository = unsProductRepository;
    }

    @GetMapping("/products")
    List<UNSProduct> all() {
        return unsProductRepository.findAll();
    }

    @PostMapping("/products")
    UNSProduct newProduct(@RequestBody UNSProduct newProduct) {
        return unsProductRepository.save(newProduct);
    }

    @GetMapping("/products/{id}")
    UNSProduct one(@RequestParam String id) {
        return unsProductRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

}
