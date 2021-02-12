package com.bikram.casestudy.controller;

import com.bikram.casestudy.model.Price;
import com.bikram.casestudy.model.Product;
import com.bikram.casestudy.repository.ProductRepository;
import com.bikram.casestudy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository repository;

    /**
     *
     * Method for Getting a product by Id
     */
    @GetMapping("/product/{id}")
    public ResponseEntity<?> getProduct(@PathVariable("id") String id) throws Exception {
        String name = "";
        try {
            name = productService.getProductName(id);
        } catch (Exception ex){
            logger.error("Product Name not found.");
            ex.printStackTrace();
        }
        Optional<Product> optional = repository.findById(id);

        //Bad product Id
        if(!optional.isPresent()){
            String error = "{\n" +
                    "   \"error\": {\n" +
                    "      \"errorCode\": “404”,\n" +
                    "      \"errorMessage\": \"Product not found.\"\n" +
                    "   }\n" +
                    "}";
            return new ResponseEntity<String>(error,HttpStatus.NOT_FOUND);
        }

        //Set product name only if it's not blank.
        if (!name.isEmpty()) {
            String finalName = name;
            optional.ifPresent(product -> {
                product.setName(finalName);
            });
        }
        return new ResponseEntity<Optional>(optional, HttpStatus.OK);
    }

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
