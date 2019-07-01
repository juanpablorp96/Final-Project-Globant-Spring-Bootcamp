package com.globant.bootcamp.shop;

import com.globant.bootcamp.shop.bussiness.service.AddressService;
import com.globant.bootcamp.shop.bussiness.service.EmployeeService;
import com.globant.bootcamp.shop.bussiness.service.ProductService;
import com.globant.bootcamp.shop.bussiness.service.StoreService;
import com.globant.bootcamp.shop.endpoint.StoreEndPoint;
import com.globant.bootcamp.shop.model.Address;
import com.globant.bootcamp.shop.model.Employee;
import com.globant.bootcamp.shop.model.Product;
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

    @MockBean
    private ProductService productService;

    @MockBean
    private EmployeeService employeeService;

    @MockBean
    private AddressService addressService;

    @Before public void setup() {
        RestAssuredMockMvc.standaloneSetup(storeEndPoint);

        Mockito.when(storeService.findById(1))
                .thenReturn(java.util.Optional.of(new Store(1, "Exito", "12345")));

        Mockito.when(productService.findById(1))
                .thenReturn(java.util.Optional.of(new Product(1, "Tv", 100, new Store(1,
                        "Exito", "12345"))));

        Mockito.when(employeeService.findById(1))
                .thenReturn(java.util.Optional.of(new Employee(1, "Juan Pablo", "320-3684334",
                        new Store(1, "Exito", "12345"))));

        Mockito.when(addressService.findById(1))
                .thenReturn(java.util.Optional.of(new Address(1, "USA", "Florida", "Miami",
                        "St 123", "010203", new Employee(1, "Juan Pablo",
                        "320-3684334", new Store(1, "Exito", "12345")))));


    }

}