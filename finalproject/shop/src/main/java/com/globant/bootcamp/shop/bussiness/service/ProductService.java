package com.globant.bootcamp.shop.bussiness.service;

import com.globant.bootcamp.shop.bussiness.repository.ProductRepository;
import com.globant.bootcamp.shop.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<Product> findById(int id_product){
        return productRepository.findById(id_product);
    }

    public Product findByIdentification(int id_product){
        return productRepository.findByIdentification(id_product);
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public List<Product> findByStoreId(int id_store){
        return productRepository.findByStoreId(id_store);
    }

    public long count(){
        return productRepository.count();
    }

    @Transactional
    public Product create(Product product){
        return productRepository.save(product);
    }

    @Transactional
    public Product update(Product product){
        return productRepository.save(product);
    }

    @Transactional
    public void deleteById(int id_product){
        productRepository.deleteById(id_product);
    }


}
