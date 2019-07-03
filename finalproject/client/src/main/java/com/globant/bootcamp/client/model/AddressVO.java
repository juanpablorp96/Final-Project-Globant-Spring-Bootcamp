package com.globant.bootcamp.client.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressVO {

    private Date creationDate;
    private Date lastUpdate;
    private int id_address;
    private String country;
    private String state;
    private String city;
    private String street;
    private String postalCode;
}
