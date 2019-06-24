package com.globant.bootcamp.shop.bussiness.repository;

import com.globant.bootcamp.shop.model.Store;
import com.globant.bootcamp.shop.resources.vo.StoreVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true) // los metodos que no tengan la anotacion Transactional van a ser tratados como READONLY
public class StoreEntityManager {

    private final StoreRepository storeRepository;

    public StoreEntityManager(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Transactional
    public Store create(Store store) {
        return this.storeRepository.save(store);
    }

    @Transactional
    public Store update(Store store) {
        return this.storeRepository.save(store);
    }

    @Transactional
    public void delete(Store store) {
        this.storeRepository.delete(store);
    }

    public Optional<Store> findById(int id) {
        return this.storeRepository.findById(id);
    }

    public List<Store> findAll() {
        return this.storeRepository.findAll();
    }

    public long count(){
        return this.storeRepository.count();
    }

    public Store findByIdentification(int id){
        return this.storeRepository.findByIdentification(id);
    }
/*
    // Create a Post
    StoreVO storeVO = new StoreVO();
    storeVO.

    // Create Comments
    Comment comment1 = new Comment("Great Post!");
comment1.setPost(post);
    Comment comment2 = new Comment("Really helpful Post. Thanks a lot!");
comment2.setPost(post);

// Add comments in the Post
post.getComments().add(comment1);
post.getComments().add(comment2);

// Save Post and Comments via the Post entity
postRepository.save(post);

 */

}
