package com.globant.bootcamp.shop.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@EqualsAndHashCode(callSuper=false)
@Data
@AllArgsConstructor
@NoArgsConstructor
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
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_store")
    @JsonIgnore
    private Store store;


}
