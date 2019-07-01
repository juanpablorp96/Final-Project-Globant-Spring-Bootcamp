package com.globant.bootcamp.shop.bussiness.repository;

import com.globant.bootcamp.shop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("Select p from Product p where p.id_product = :id_product")
    Product findByIdentification(@Param("id_product") int id_product);

    @Query("Select p from Product p where p.store.id_store = :id_store")
    List<Product> findByStoreId(@Param("id_store") int id_store);


}
