package com.uns.api.backend;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.uns.api.backend.model.UNSProduct;
import com.uns.api.backend.repository.UNSProductRepository;

@SpringBootApplication
@EnableMongoRepositories
public class BackendApplication implements CommandLineRunner {

	@Autowired
	UNSProductRepository unsProductsRepository;

	List<UNSProduct> products = new ArrayList<UNSProduct>();

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	public void showAllProducts() {
		products = unsProductsRepository.findAll();
		products.forEach(product -> System.out.println(getProductDetails(product)));
	}

	public String getProductDetails(UNSProduct product) {
		return "Product: " + product.getTitle();
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		showAllProducts();
	}

}
