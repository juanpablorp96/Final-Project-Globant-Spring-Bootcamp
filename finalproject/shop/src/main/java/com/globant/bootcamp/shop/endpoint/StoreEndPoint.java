package com.globant.bootcamp.shop.endpoint;

import com.globant.bootcamp.shop.bussiness.repository.ProductRepository;
import com.globant.bootcamp.shop.bussiness.repository.StoreRepository;
import com.globant.bootcamp.shop.model.Product;
import com.globant.bootcamp.shop.model.Store;
import com.globant.bootcamp.shop.resources.vo.ProductVO;
import com.globant.bootcamp.shop.resources.vo.StoreVO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/stores")
public class StoreEndPoint {


    private final StoreRepository storeRepository;
    private final ProductRepository productRepository;

    public StoreEndPoint(StoreRepository storeRepository, ProductRepository productRepository) {
        this.storeRepository = storeRepository;
        this.productRepository = productRepository;
    }


    @GetMapping("/total")
    public long getTotalStores() {
        return storeRepository.count();
    }

    @GetMapping("/{id_store}")
    public Optional<Store> findStoreById(@PathVariable("id_store") @NotNull int id_store) {
        return storeRepository.findById(id_store);
    }

    @GetMapping("/{id_store}/products/{id_product}")
    public Store findStoreProductById(@PathVariable("id_store") @NotNull int id_store, @PathVariable("id_product") int id_product) {
        /*
        Store store = storeRepository.findById(id_store).get();
        Product product = productRepository.findById(id_product).get();
        if(store.getProducts().contains(product)){
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

         */
        Optional<Store> store = storeRepository.findById(id_store);
        Store store2 = null;
        if(store.isPresent()){
            store2 = store.get();
        }


        //System.out.println(store.toString());
        for(Product product : store2.getProducts()){
            System.out.print(product);
        }

        return store2;

    }

    @PostMapping
    public ResponseEntity<Store> createStore(@RequestBody StoreVO storeVO) {
        Store store = new Store();
        store.setId_store(storeVO.getId_store());
        store.setName(storeVO.getName());
        store.setAddress(storeVO.getAddress());
        store.setPhone(storeVO.getPhone());
        Set<Product> productSet = new HashSet<>();
        Product product = new Product();
        product.setId_product(1209);
        product.setName("2 soy un producto");
        productSet.add(product);
        store.setProducts(productSet);

        return new ResponseEntity<>(this.storeRepository.save(store), HttpStatus.CREATED);
    }

    @PostMapping("/{id_store}/products")
    public ResponseEntity<Product> createStore(@PathVariable("id_store") @NotNull int id_store, @RequestBody ProductVO productVO) {

        Store store = storeRepository.findByIdentification(id_store);
        Product product = new Product();
        product.setId_product(productVO.getId_product());
        product.setName(productVO.getName());
        product.setStock(productVO.getStock());
        product.setStore(store);

        /*
        //Set<Product> products = store.getProducts();
        Set<Product> products = new HashSet<>();
        products.add(product);
        store.setProducts(products);
        storeRepository.save(store);

         */


        return new ResponseEntity<>(productRepository.save(product), HttpStatus.CREATED);
    }

    @PutMapping("/{id_store}")
    @ApiOperation(value = "Update store", notes = "Service to update a store")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Store updated success"),
            @ApiResponse(code = 404, message = "Store not found") })
    public ResponseEntity<Store> updateStore(@PathVariable("id_store") int id_store,
                                                 StoreVO storeVO) {

        Store store = this.storeRepository.findByIdentification(id_store);

        if (store == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } else {
            store.setName(storeVO.getName());
            store.setAddress(storeVO.getAddress());
            store.setPhone(storeVO.getPhone());
        }

        return new ResponseEntity<>(this.storeRepository.save(store), HttpStatus.OK);
    }


}
