package com.globant.bootcamp.shop.endpoint;

import com.globant.bootcamp.shop.bussiness.service.AddressService;
import com.globant.bootcamp.shop.model.Address;
import com.globant.bootcamp.shop.resources.vo.AddressVO;
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
@RequestMapping("/address")
public class AddressEndPoint {

    private final AddressService addressService;

    public AddressEndPoint(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public List<Address> getProducts() {
        return addressService.findAll();
    }

    @GetMapping("/total")
    public long getTotalProducts() {
        return addressService.count();
    }

    @GetMapping("/{id_address}")
    public Address findProductById(@PathVariable("id_address") @NotNull int id_address) {
        return addressService.findByIdentification(id_address);
    }

    @PostMapping
    public ResponseEntity<Address> createProduct(@RequestBody AddressVO addressVO) {
        Address address = new Address();
        address.setId_address(addressVO.getId_address());
        address.setCountry(addressVO.getCountry());
        address.setState(addressVO.getState());
        address.setCity(addressVO.getCity());
        address.setStreet(addressVO.getStreet());
        address.setPostalCode(addressVO.getPostalCode());

        return new ResponseEntity<>(this.addressService.create(address), HttpStatus.CREATED);
    }

    @PutMapping("/{id_address}")
    @ApiOperation(value = "Update address", notes = "Service to update an address")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Address updated success"),
            @ApiResponse(code = 404, message = "Address not found") })
    public ResponseEntity<Address> updateAddress(@PathVariable("id_address") @NotNull int id_address,
                                                 @RequestBody AddressVO addressVO) {

        Optional<Address> address = addressService.findById(id_address);

        if (address.isPresent()) {
            address.get().setCountry(addressVO.getCountry());
            address.get().setState(addressVO.getState());
            address.get().setCity(addressVO.getCity());
            address.get().setStreet(addressVO.getStreet());
            address.get().setPostalCode(addressVO.getPostalCode());

            return new ResponseEntity<>(addressService.update(address.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id_address}")
    public HttpStatus deleteAddress(@PathVariable("id_address") @NotNull int id_address){
        if(addressService.findById(id_address).isPresent()){
            addressService.deleteById(id_address);

            return HttpStatus.OK;
        }
        return HttpStatus.BAD_REQUEST;
    }


}
