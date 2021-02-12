package com.bikram.casestudy.service;

import com.bikram.casestudy.repository.ProductRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Bikram Das
 *
 * JPA Repository class for Product.
 */
@Service
public class ProductService {

    @Autowired
    ProductRepository repository;

    public String getProductName(String productId) throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        //URL to get product name
        String URL
                = "https://redsky.target.com/v3/pdp/tcin/"+productId+"?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics&key=candidate#_blank";

        ResponseEntity<String> response
                = restTemplate.getForEntity(URL , String.class);

        //If there are errors send blank product name
        if(!response.getStatusCode().equals(HttpStatus.OK))
            return "";

        JSONObject json = new JSONObject(response.getBody().toString());

        //Scrape product name from Response
        return json.getJSONObject("product").getJSONObject("item").getJSONObject("product_description").getString("title");
    }

}
