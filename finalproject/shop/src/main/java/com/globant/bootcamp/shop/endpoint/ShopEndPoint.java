package com.globant.bootcamp.shop.endpoint;

import com.globant.bootcamp.shop.bussiness.repository.ProductEntityManager;
import com.globant.bootcamp.shop.bussiness.service.ShopFacade;
import com.globant.bootcamp.shop.model.Product;
import com.globant.bootcamp.shop.resources.vo.ProductVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ShopEndPoint {

    ShopFacade shopFacade = new ShopFacade();

    private final ProductEntityManager productEntityManager;

    public ShopEndPoint(ProductEntityManager productEntityManager) {
        this.productEntityManager = productEntityManager;
    }


    @GetMapping("/total")
    public int getTotalProducts() {
        return shopFacade.getTotalProducts();
    }

    @GetMapping("/{id_product}")
    public Optional<Product> findProductById(@PathVariable("id_product") @NotNull int id_product) {
        return productEntityManager.findById(id_product);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductVO productVO) {
        Product product = new Product();
        product.setId(productVO.getId());
        product.setName(productVO.getName());
        product.setStock(productVO.getStock());


        return new ResponseEntity<>(this.productEntityManager.create(product), HttpStatus.CREATED);
    }


}
