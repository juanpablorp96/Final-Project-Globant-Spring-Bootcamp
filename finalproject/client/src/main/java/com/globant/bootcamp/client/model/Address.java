package com.globant.bootcamp.client.model;

import lombok.Data;

@Data
public class Address {

    private int id_address;
    private String country;
    private String state;
    private String city;
    private String street;
    private String postalCode;
}
