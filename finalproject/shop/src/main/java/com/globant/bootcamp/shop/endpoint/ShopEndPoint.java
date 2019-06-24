package com.globant.bootcamp.shop.endpoint;

import com.globant.bootcamp.shop.bussiness.repository.ProductEntityManager;
import com.globant.bootcamp.shop.bussiness.service.ShopFacade;
import com.globant.bootcamp.shop.model.Product;
import com.globant.bootcamp.shop.resources.vo.ProductVO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    public long getTotalProducts() {
        return productEntityManager.count();
    }

    @GetMapping("/{id_product}")
    public Optional<Product> findProductById(@PathVariable("id_product") @NotNull int id_product) {
        return productEntityManager.findById(id_product);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductVO productVO) {
        Product product = new Product();
        product.setId_product(productVO.getId_product());
        product.setName(productVO.getName());
        product.setStock(productVO.getStock());


        return new ResponseEntity<>(this.productEntityManager.create(product), HttpStatus.CREATED);
    }

    @PutMapping("/{id_product}")
    @ApiOperation(value = "Update product", notes = "Service to update a product")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Product updated success"),
            @ApiResponse(code = 404, message = "Product not found") })
    public ResponseEntity<Product> updateProduct(@PathVariable("id_product") int id_product,
                                                 ProductVO productVO) {

        Product product = this.productEntityManager.findByIdentification(id_product);

        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } else {
            product.setName(productVO.getName());
            product.setStock(productVO.getStock());
        }

        return new ResponseEntity<>(this.productEntityManager.update(product), HttpStatus.OK);
    }


}
