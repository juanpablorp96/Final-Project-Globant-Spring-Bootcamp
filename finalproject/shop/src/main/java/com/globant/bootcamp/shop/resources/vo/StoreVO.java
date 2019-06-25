package com.globant.bootcamp.shop.resources.vo;

import com.globant.bootcamp.shop.model.Employee;
import com.globant.bootcamp.shop.model.Product;
import lombok.Data;

import java.util.Set;

@Data
public class StoreVO {

    private int id_store;
    private String name;
    private String address;
    private String phone;
    private Set<Product> products;
    private Set<Employee> employees;
}
