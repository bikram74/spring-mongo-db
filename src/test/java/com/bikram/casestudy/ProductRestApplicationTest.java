package com.bikram.casestudy;

import com.bikram.casestudy.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Bikram Das
 *
 * Test Product Rest APIs
 */
@SpringBootTest
@AutoConfigureMockMvc
public class ProductRestApplicationTest {

    private static final Logger logger = LoggerFactory.getLogger(ProductRestApplicationTest.class);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    public void deleteAllBeforeTests() throws Exception {
        logger.info("Running Tests.........");
    }

    @Test
    public void shouldReturnProdRepositoryIndex() throws Exception {

        mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk()).andExpect(
                jsonPath("$._links.product").exists());
    }

    @Test
    public void shouldReturnProducts() throws Exception {

        mockMvc.perform(get("/product")).andDo(print()).andExpect(status().isOk()).andExpect(
                jsonPath("$._embedded.product").exists());
    }

    @Test
    public void shouldReturnAProduct() throws Exception {

        mockMvc.perform(get("/product/13860428")).andDo(print()).andExpect(
                status().isOk()).andExpect(
                jsonPath("$.id").value("13860428")).andExpect(
                jsonPath("$.name").value("")).andExpect(
                jsonPath("$.current_price.value").value(799.99)).andExpect(
                jsonPath("$.current_price.currency_code").value("USD"));
    }

    @Test
    public void productNotFound() throws Exception {

        mockMvc.perform(get("/product/12345678")).andDo(print()).andExpect(
                status().isNotFound());
    }


    @Test
    public void shouldCreateProduct() throws Exception {

        String request = "{ \n" +
                "  \"id\" : \"72837217\",\n" +
                "  \"name\": \"My TV xxx\",\n" +
                "  \"current_price\": {\n" +
                "    \"value\": 199.49,\n" +
                "    \"currency_code\": \"USD\"\n" +
                "  }\n" +
                "}";

        mockMvc.perform(post("/product").content(
                request)).andExpect(
                status().isCreated()).andExpect(
                header().string("Location", containsString("product/")));
        productRepository.deleteById("72837217");
    }

    @Test
    public void shouldUpdateEntity() throws Exception {

        String request = "{ \n" +
                "  \"id\" : \"12954218\",\n" +
                "  \"name\": \"Kraft Macaroni &#38; Cheese Dinner Original - 7.25oz\",\n" +
                "  \"current_price\": {\n" +
                "    \"value\": 199.49,\n" +
                "    \"currency_code\": \"USD\"\n" +
                "  }\n" +
                "}";

        MvcResult mvcResult = mockMvc.perform(post("/product").content(
                request)).andExpect(
                status().isCreated()).andReturn();

        String location = mvcResult.getResponse().getHeader("Location");

        String updateRequest = "{ \n" +
                "  \"name\": \"My TV\",\n" +
                "  \"current_price\": {\n" +
                "    \"value\": 299.49,\n" +
                "    \"currency_code\": \"USD\"\n" +
                "  }\n" +
                "}";

        MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json", java.nio.charset.Charset.forName("UTF-8"));

        mockMvc.perform(put(location).contentType(MEDIA_TYPE_JSON_UTF8).content(
                updateRequest)).andExpect(
                status().isOk());

        mockMvc.perform(get(location)).andExpect(status().isOk()).andExpect(
                jsonPath("$.name").value("My TV")).andExpect(
                jsonPath("$.current_price.value").value(299.49));

        productRepository.deleteById("12954218");
    }
}
