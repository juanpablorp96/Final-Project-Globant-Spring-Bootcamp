package com.globant.bootcamp.shop.endpoint;

import com.globant.bootcamp.shop.bussiness.repository.StoreRepository;
import com.globant.bootcamp.shop.model.Store;
import com.globant.bootcamp.shop.resources.vo.StoreVO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@RestController
@RequestMapping("/stores")
public class StoreEndPoint {


    private final StoreRepository storeRepository;

    public StoreEndPoint(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }


    @GetMapping("/total")
    public long getTotalStores() {
        return storeRepository.count();
    }

    @GetMapping("/{id_store}")
    public Optional<Store> findStoreById(@PathVariable("id_store") @NotNull int id_store) {
        return storeRepository.findById(id_store);
    }

    @PostMapping
    public ResponseEntity<Store> createStore(@RequestBody StoreVO storeVO) {
        Store store = new Store();
        store.setId_store(storeVO.getId_store());
        store.setName(storeVO.getName());
        store.setAddress(storeVO.getAddress());
        store.setPhone(storeVO.getPhone());
        store.setProducts(storeVO.getProducts());

        return new ResponseEntity<>(this.storeRepository.save(store), HttpStatus.CREATED);
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
