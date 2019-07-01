package com.globant.bootcamp.shop.endpoint;

import com.globant.bootcamp.shop.bussiness.service.ProductService;
import com.globant.bootcamp.shop.model.Product;
import com.globant.bootcamp.shop.resources.vo.ProductVO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductEndPoint {

    private final ProductService productService;

    public ProductEndPoint(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProducts() {
        return productService.findAll();
    }

    @GetMapping("/total")
    public long getTotalProducts() {
        return productService.count();
    }

    @GetMapping("/{id_product}")
    public Product findProductById(@PathVariable("id_product") @NotNull int id_product) {
        return productService.findByIdentification(id_product);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductVO productVO) {
        Product product = new Product();
        product.setId_product(productVO.getId_product());
        product.setName(productVO.getName());
        product.setStock(productVO.getStock());

        return new ResponseEntity<>(this.productService.create(product), HttpStatus.CREATED);
    }

    @PutMapping("/{id_product}")
    @ApiOperation(value = "Update product", notes = "Service to update a product")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Product updated success"),
            @ApiResponse(code = 404, message = "Product not found") })
    public ResponseEntity<Product> updateProduct(@PathVariable("id_product") @NotNull int id_product,
                                                 @RequestBody ProductVO productVO) {

        Optional<Product> product = productService.findById(id_product);

        if (product.isPresent()) {
            product.get().setName(productVO.getName());
            product.get().setStock(productVO.getStock());

            return new ResponseEntity<>(productService.update(product.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id_product}")
    public HttpStatus deleteProduct(@PathVariable("id_product") @NotNull int id_product){
        if(productService.findById(id_product).isPresent()){
            productService.deleteById(id_product);

            return HttpStatus.OK;
        }
        return HttpStatus.BAD_REQUEST;
    }


}
