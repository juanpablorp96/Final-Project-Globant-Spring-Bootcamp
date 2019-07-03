package com.globant.bootcamp.shop.bussiness.service;

import com.globant.bootcamp.shop.bussiness.repository.StoreRepository;
import com.globant.bootcamp.shop.model.Store;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class StoreService {

    private final StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public Optional<Store> findById(int id_store){
        return storeRepository.findById(id_store);
    }

    public Store findByIdentification(int id_store){
        return storeRepository.findByIdentification(id_store);
    }

    public List<Store> findAll(){
        return storeRepository.findAll();
    }

    public long count(){
        return storeRepository.count();
    }

    @Transactional
    public Store create(Store store){
        return storeRepository.save(store);
    }

    @Transactional
    public Store update(Store store){
        return storeRepository.save(store);
    }

    @Transactional
    public void deleteById(int id_store){
        storeRepository.deleteById(id_store);
    }


}
