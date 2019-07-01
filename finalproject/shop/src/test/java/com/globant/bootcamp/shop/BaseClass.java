package com.globant.bootcamp.shop;

import com.globant.bootcamp.shop.bussiness.service.StoreService;
import com.globant.bootcamp.shop.endpoint.StoreEndPoint;
import com.globant.bootcamp.shop.model.Store;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

/**
 * To verify an API provider (the Spring controller in our case), Spring Cloud Contract automatically generates JUnit
 * tests from a given contract. In order to give these automatically generated tests a working context, we need to
 * create a base test class which is subclassed by all generated tests:
 *
 * In this base class, we’re setting up a Spring Boot application with @SpringBootTest and are mocking away the
 * CustomerService so that it always returns the person specified in the contract. Then, we set up RestAssured so that the
 * generated tests can simply use RestAssured to send requests against our controller.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShopApplication.class)
public abstract class BaseClass {

    @Autowired
    private StoreEndPoint storeEndPoint;

    @MockBean
    private StoreService storeService;

    @Before public void setup() {
        RestAssuredMockMvc.standaloneSetup(storeEndPoint);

        Mockito.when(storeService.findByIdentification(1))
                .thenReturn(new Store(1, "Exito", "12345"));
    }

}