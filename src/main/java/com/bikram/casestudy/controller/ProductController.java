package com.bikram.casestudy.controller;

import com.bikram.casestudy.model.Price;
import com.bikram.casestudy.model.Product;
import com.bikram.casestudy.repository.ProductRepository;
import com.bikram.casestudy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * @author Bikram Das
 *
 * Controller Class for Product REST APIs
 */

@BasePathAwareController
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository repository;

    /**
     *
     * Method for Updating a product by Id. Consumers shoud use PUT Method for REST call.
     */

    @PutMapping("/product/{id}")
    public ResponseEntity<?> updateProduct(@RequestBody Product product, @PathVariable("id") String id) throws Exception {
        Product dbRecord = repository.findById(id).
                orElseThrow(() -> new Exception("Product with id:" +id+ " not found"));

        dbRecord.setName(product.getName());
        dbRecord.setCurrent_price(new Price(product.getCurrent_price().getValue(), product.getCurrent_price().getCurrency_code()));
        Product saved = repository.save(dbRecord);
        return new ResponseEntity<Product>(saved, HttpStatus.OK);
    }
}
