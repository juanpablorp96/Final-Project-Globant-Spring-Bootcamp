package com.globant.bootcamp.client.endpoint;

import com.globant.bootcamp.client.model.*;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class ClientEndPoint {

    private final RestTemplate restTemplate;

    public ClientEndPoint(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @RequestMapping("/stores/{id_store}")
    public String getStore(@PathVariable("id_store") int id_store) {

        Store store = this.restTemplate.getForObject("http://localhost:8000/stores/{id_store}", Store.class, id_store);
        return "Welcome to store: " + store.getName();
    }

    @RequestMapping("/stores/{id_store}/products/{id_product}")
    public String getProduct(@PathVariable("id_store") int id_store, @PathVariable("id_product") int id_product) {

        Product product = this.restTemplate.getForObject("http://localhost:8000/stores/{id_store}/products/{id_product}",
                Product.class, id_store, id_product);
        return "Product: " + product.getName() + "  Stock: " + product.getStock();
    }

    @RequestMapping("/stores/{id_store}/employees/{id_employee}")
    public String getEmployee(@PathVariable("id_store") int id_store, @PathVariable("id_employee") int id_employee) {

        Employee employee = this.restTemplate.getForObject("http://localhost:8000/stores/{id_store}/employees/{id_employee}",
                Employee.class, id_store, id_employee);
        return "Employee: " + employee.getName() + "  Phone: " + employee.getPhone();
    }

    @RequestMapping("/stores/{id_store}/employees/{id_employee}/address/{id_address}")
    public String getAddress(@PathVariable("id_store") int id_store, @PathVariable("id_employee") int id_employee,
                             @PathVariable("id_address") int id_address) {

        Address address = this.restTemplate.getForObject(
                "http://localhost:8000/stores/{id_store}/employees/{id_employee}/address/{id_address}",
                Address.class, id_store, id_employee, id_address);
        return "Country: " + address.getCountry() + "  State: " + address.getState() + "  City: " + address.getCity() +
                "  Street: " + address.getStreet() + "  Postal Code: " + address.getPostalCode();
    }

    @PostMapping("/stores")
    public String createStore(@RequestBody Store store) {

        StoreVO storeVO = this.restTemplate.postForObject("http://localhost:8000/stores", store, StoreVO.class);

        return "Store: " + storeVO.getName() + "  Created at " + storeVO.getCreationDate();

    }

    @DeleteMapping("/stores/{id_store}")
    public String deleteStore(@PathVariable("id_store") int id_store) {

        this.restTemplate.delete("http://localhost:8000/stores/{id_store}", id_store);
        return "Store with ID : " + id_store + " deleted";
    }

    @PostMapping("/stores/{id_store}/products")
    public String createStoreProduct(@PathVariable("id_store") int id_store, @RequestBody Product product) {

        ProductVO productVO = this.restTemplate.postForObject("http://localhost:8000/stores/{id_store}/products",
                product, ProductVO.class, id_store);

        return "Product: " + productVO.getName() + "  Created at " + productVO.getCreationDate();

    }

    @DeleteMapping("/stores/{id_store}/products/{id_product}")
    public String deleteProduct(@PathVariable("id_store") int id_store, @PathVariable("id_product") int id_product) {

        this.restTemplate.delete("http://localhost:8000/stores/{id_store}/products/{id_product}", id_store, id_product);
        return "Product with ID : " + id_product + " deleted";
    }

    @PostMapping("/stores/{id_store}/employees")
    public String createStoreEmployee(@PathVariable("id_store") int id_store, @RequestBody Employee employee) {

        EmployeeVO employeeVO = this.restTemplate.postForObject("http://localhost:8000/stores/{id_store}/employees",
                employee, EmployeeVO.class, id_store);

        return "Employee: " + employeeVO.getName() + "  Created at " + employeeVO.getCreationDate();

    }

    @DeleteMapping("/stores/{id_store}/employees/{id_employee}")
    public String deleteEmployee(@PathVariable("id_store") int id_store, @PathVariable("id_employee") int id_employee) {

        this.restTemplate.delete("http://localhost:8000/stores/{id_store}/employees/{id_employee}", id_store, id_employee);
        return "Employee with ID : " + id_employee + " deleted";
    }



}