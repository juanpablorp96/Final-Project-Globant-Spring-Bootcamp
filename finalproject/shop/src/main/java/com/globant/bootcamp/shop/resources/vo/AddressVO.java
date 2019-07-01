package com.globant.bootcamp.shop.resources.vo;

import lombok.Data;


@Data
public class AddressVO {

    private int id_address;
    private String country;
    private String state;
    private String city;
    private String street;
    private String postalCode;

}
