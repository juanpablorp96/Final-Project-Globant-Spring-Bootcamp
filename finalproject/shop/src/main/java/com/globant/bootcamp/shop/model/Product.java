package com.globant.bootcamp.shop.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@Entity
@Table(name = "product")
@NamedQuery(name="Product.findByIdentification", query="Select p from Product p where p.id_product = ?1" )
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Product extends AuditModel{
    @Id
    //@GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id_product;
    private String name;
    private int stock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_store")
    @JsonManagedReference
    private Store store;

}
