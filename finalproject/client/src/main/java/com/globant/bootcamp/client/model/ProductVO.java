package com.globant.bootcamp.client.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductVO {

    private Date creationDate;
    private Date lastUpdate;
    private int id_product;
    private String name;
    private int stock;
}
