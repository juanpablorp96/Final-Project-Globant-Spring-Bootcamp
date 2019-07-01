package com.globant.bootcamp.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@Entity
@Table(name = "address")
@NamedQuery(name="Address.findByIdentification", query="Select a from Address a where a.id_address = ?1" )
public class Address extends AuditModel{
    @Id
    //@GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id_address;
    private String country;
    private String state;
    private String city;
    private String street;
    private String postalCode;

    @OneToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_employee")
    @JsonIgnore
    private Employee employee;
}
