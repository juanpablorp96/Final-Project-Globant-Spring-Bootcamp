package com.globant.bootcamp.shop.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "store")
@NamedQuery(name="Store.findByIdentification", query="Select s from Store s where s.id_store = ?1" )
public class Store extends AuditModel{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id_store;
    private String name;
    private String address;
    private String phone;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "store")
    private Set<Product> products;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "store")
    private Set<Employee> employees;

}
