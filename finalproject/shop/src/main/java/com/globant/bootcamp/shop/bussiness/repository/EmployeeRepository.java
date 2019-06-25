package com.globant.bootcamp.shop.bussiness.repository;

import com.globant.bootcamp.shop.model.Employee;
import com.globant.bootcamp.shop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Employee findByIdentification(int id);


}
