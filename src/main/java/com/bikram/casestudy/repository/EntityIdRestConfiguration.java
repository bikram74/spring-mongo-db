package com.bikram.casestudy.repository;

import com.bikram.casestudy.model.Product;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

/**
 * @author Bikram Das
 *
 * Spring confuguration class which allows to control what is exposed in REST response.
 * This class is used to provice Id as part of REST reponse.
 */
@Configuration
public class EntityIdRestConfiguration implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        //Provide Id in Product REST Response.
        config.exposeIdsFor(Product.class);
    }
}
