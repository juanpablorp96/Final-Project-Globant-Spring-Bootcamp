package com.globant.bootcamp.shop.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "employee")
@NamedQuery(name="Employee.findByIdentification", query="Select e from Employee e where e.id_employee = ?1" )
public class Employee extends AuditModel{
    @Id
    //@GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id_employee;
    private String name;
    private String address;
    private String phone;

    @ManyToOne
    @JoinColumn(name = "id_store")
    @JsonManagedReference
    private Store store;
}
