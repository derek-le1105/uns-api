package com.uns.api.backend;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.uns.api.backend.products.model.UNSProduct;

@SpringBootApplication
@EnableMongoRepositories
public class BackendApplication {

	List<UNSProduct> products = new ArrayList<UNSProduct>();

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}
