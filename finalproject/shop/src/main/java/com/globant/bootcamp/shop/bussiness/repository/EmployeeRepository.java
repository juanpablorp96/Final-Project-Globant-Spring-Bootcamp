package com.globant.bootcamp.shop.bussiness.repository;

import com.globant.bootcamp.shop.model.Employee;
import com.globant.bootcamp.shop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("Select e from Employee e where e.id_employee = :id_employee")
    Employee findByIdentification(@Param("id_employee") int id_employee);

    @Query("Select e from Employee e where e.store.id_store = :id_store")
    List<Employee> findByStoreId(@Param("id_store") int id_store);


}
