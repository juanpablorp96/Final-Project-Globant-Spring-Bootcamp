package com.globant.bootcamp.shop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@EqualsAndHashCode(callSuper=false)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "store")
@NamedQuery(name="Store.findByIdentification", query="Select s from Store s where s.id_store = ?1" )
public class Store extends AuditModel{

    @Id
    //@GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id_store;
    private String name;
    private String phone;


    /*

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "store")
    @JsonBackReference
    private Set<Product> products = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "store")
    @JsonBackReference
    private Set<Employee> employees = new HashSet<>();


    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
        product.setStore(null);
    }

     */

}
