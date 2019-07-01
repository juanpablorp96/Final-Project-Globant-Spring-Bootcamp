package com.globant.bootcamp.shop.bussiness.repository;

import com.globant.bootcamp.shop.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    @Query("Select a from Address a where a.id_address = :id_address")
    Address findByIdentification(@Param("id_address") int id_address);

    @Query("Select a from Address a where a.employee.id_employee = :id_employee")
    Address findByEmployeeId(@Param("id_employee") int id_employee);

}
