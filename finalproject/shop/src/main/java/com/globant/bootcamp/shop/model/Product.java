package com.globant.bootcamp.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "product")
@NamedQuery(name="Product.findByIdentification", query="Select p from Product p where p.id = ?1" )
public class Product {
    @Id
    private int id;
    private String name;
    private int stock;
}


//@GeneratedValue(generator="system-uuid")
//@GenericGenerator(name="system-uuid", strategy="uuid2")