package com.globant.bootcamp.client.endpoint;

import com.globant.bootcamp.client.model.Store;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/client")
public class ClientEndPoint {

    private final RestTemplate restTemplate;

    public ClientEndPoint(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @GetMapping("/stores/{id_store}")
    public String getStore(@PathVariable("id_store") int id_store) {

        Store store = this.restTemplate.getForObject("http://localhost:8000/stores/{id_store}", Store.class, id_store);
        return "Welcome to " + store.getName();
    }

}