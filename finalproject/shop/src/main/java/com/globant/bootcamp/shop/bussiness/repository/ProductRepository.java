package com.globant.bootcamp.shop.bussiness.repository;

import com.globant.bootcamp.shop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findByIdentification(int id);


}
