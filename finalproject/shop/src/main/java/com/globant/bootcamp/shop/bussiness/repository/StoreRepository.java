package com.globant.bootcamp.shop.bussiness.repository;

import com.globant.bootcamp.shop.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Integer> {

    @Query("Select s from Store s where s.id_store = :id_store")
    Store findByIdentification(@Param("id_store") int id_store);
}
