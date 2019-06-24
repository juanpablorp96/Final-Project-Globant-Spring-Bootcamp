package com.globant.bootcamp.shop.bussiness.repository;

import com.globant.bootcamp.shop.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true) // los metodos que no tengan la anotacion Transactional van a ser tratados como READONLY
public class ProductEntityManager {

    private final ProductRepository productRepository;

    public ProductEntityManager(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public Product create(Product product) {
        return this.productRepository.save(product);
    }

    @Transactional
    public Product update(Product product) {
        return this.productRepository.save(product);
    }

    @Transactional
    public void delete(Product product) {
        this.productRepository.delete(product);
    }

    public Optional<Product> findById(int id) {
        return this.productRepository.findById(id);
    }

    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    public long count(){
        return this.productRepository.count();
    }

    public Product findByIdentification(int id){
        return this.productRepository.findByIdentification(id);
    }

}
