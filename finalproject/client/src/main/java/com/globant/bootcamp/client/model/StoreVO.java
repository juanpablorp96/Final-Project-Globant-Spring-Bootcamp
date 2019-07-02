package com.globant.bootcamp.client.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreVO {

    private Date creationDate;
    private Date lastUpdate;
    private int id_store;
    private String name;
    private String phone;
}
