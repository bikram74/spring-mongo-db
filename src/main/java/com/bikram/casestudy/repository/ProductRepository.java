
package com.bikram.casestudy.repository;

import com.bikram.casestudy.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author Bikram Das
 *
 * JPA Repository class for Product.
 */
@RepositoryRestResource(collectionResourceRel = "product", path = "product")
public interface ProductRepository extends MongoRepository<Product, String> {

}
