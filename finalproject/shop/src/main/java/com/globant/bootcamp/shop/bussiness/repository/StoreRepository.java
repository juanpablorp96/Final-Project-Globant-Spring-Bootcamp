package com.globant.bootcamp.shop.bussiness.repository;

import com.globant.bootcamp.shop.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Integer> {

    Store findByIdentification(int id);
}
