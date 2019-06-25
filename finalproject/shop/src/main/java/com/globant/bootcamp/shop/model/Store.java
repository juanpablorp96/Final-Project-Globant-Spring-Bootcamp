package com.globant.bootcamp.shop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Entity
@Table(name = "store")
@NamedQuery(name="Store.findByIdentification", query="Select s from Store s where s.id_store = ?1" )
public class Store extends AuditModel{

    @Id
    //@GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id_store;
    private String name;
    private String address;
    private String phone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "store")
    @JsonBackReference
    private Set<Product> products;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "store")
    @JsonBackReference
    private Set<Employee> employees;

}
