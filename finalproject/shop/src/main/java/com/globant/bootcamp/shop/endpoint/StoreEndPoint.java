package com.globant.bootcamp.shop.endpoint;

import com.globant.bootcamp.shop.bussiness.service.AddressService;
import com.globant.bootcamp.shop.bussiness.service.EmployeeService;
import com.globant.bootcamp.shop.bussiness.service.ProductService;
import com.globant.bootcamp.shop.bussiness.service.StoreService;
import com.globant.bootcamp.shop.model.Address;
import com.globant.bootcamp.shop.model.Employee;
import com.globant.bootcamp.shop.model.Product;
import com.globant.bootcamp.shop.model.Store;
import com.globant.bootcamp.shop.resources.vo.AddressVO;
import com.globant.bootcamp.shop.resources.vo.EmployeeVO;
import com.globant.bootcamp.shop.resources.vo.ProductVO;
import com.globant.bootcamp.shop.resources.vo.StoreVO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/stores")
public class StoreEndPoint {


    private final StoreService storeService;
    private final ProductService productService;
    private  final EmployeeService employeeService;
    private final AddressService addressService;

    public StoreEndPoint(StoreService storeService, ProductService productService, EmployeeService employeeService,
                         AddressService addressService) {
        this.storeService = storeService;
        this.productService = productService;
        this.employeeService = employeeService;
        this.addressService = addressService;
    }


    @GetMapping("/total")
    public long getTotalStores() {
        return storeService.count();
    }

    @PostMapping
    public ResponseEntity<Store> createStore(@RequestBody StoreVO storeVO) {
        Store store = new Store();
        store.setId_store(storeVO.getId_store());
        store.setName(storeVO.getName());
        store.setPhone(storeVO.getPhone());

        return new ResponseEntity<>(this.storeService.create(store), HttpStatus.CREATED);
    }

    @GetMapping("/{id_store}")
    public ResponseEntity<Store> findStoreById(@PathVariable("id_store") @NotNull int id_store) {
        Optional<Store> store = storeService.findById(id_store);
        if(store.isPresent()){
            return new ResponseEntity<>(store.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public List<Store> getStores() {
        return storeService.findAll();
    }

    @PutMapping("/{id_store}")
    @ApiOperation(value = "Update store", notes = "Service to update a store")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Store updated success"),
            @ApiResponse(code = 404, message = "Store not found") })
    public ResponseEntity<Store> updateStore(@PathVariable("id_store") @NotNull int id_store, @RequestBody StoreVO storeVO) {

        Optional<Store> store = storeService.findById(id_store);

        if (store.isPresent()) {
            store.get().setId_store(storeVO.getId_store());
            store.get().setName(storeVO.getName());
            store.get().setPhone(storeVO.getPhone());

            return new ResponseEntity<>(storeService.update(store.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @PatchMapping("/{id_store}")
    @ApiOperation(value = "Update store", notes = "Service to update a store")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Store updated success"),
            @ApiResponse(code = 404, message = "Store not found") })
    public ResponseEntity<Store> updateStorePatch(@PathVariable("id_store") @NotNull int id_store, @RequestBody StoreVO storeVO) {

        Optional<Store> store = storeService.findById(id_store);

        if (store.isPresent()) {
            if(storeVO.getName() != null) {
                store.get().setName(storeVO.getName());
            }
            if(storeVO.getPhone() != null) {
                store.get().setPhone(storeVO.getPhone());
            }

            return new ResponseEntity<>(storeService.update(store.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @DeleteMapping("/{id_store}")
    public HttpStatus deleteStore(@PathVariable("id_store") @NotNull int id_store){

        if(storeService.findById(id_store).isPresent()){
            storeService.deleteById(id_store);

            return HttpStatus.OK;
        }
        return HttpStatus.BAD_REQUEST;
    }

    @PostMapping("/{id_store}/products")
    public ResponseEntity<Product> createStoreProduct(@PathVariable("id_store") @NotNull int id_store,
                                                      @RequestBody ProductVO productVO) {

        Optional<Store> store = storeService.findById(id_store);
        if(store.isPresent()) {
            Product product = new Product();
            product.setId_product(productVO.getId_product());
            product.setName(productVO.getName());
            product.setStock(productVO.getStock());
            product.setStore(store.get());

            return new ResponseEntity<>(productService.create(product), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id_store}/products")
    public ResponseEntity<List<Product>> getStoreProducts(@PathVariable("id_store") @NotNull int id_store) {

        if(storeService.findById(id_store).isPresent()){
            return new ResponseEntity<>(productService.findByStoreId(id_store), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/{id_store}/products/{id_product}")
    public ResponseEntity<Product> findStoreProductById(@PathVariable("id_store") @NotNull int id_store,
                                                        @PathVariable("id_product") @NotNull int id_product) {

        if(storeService.findById(id_store).isPresent()){
            Optional<Product> product = productService.findById(id_product);
            if(product.isPresent()){
                return new ResponseEntity<>(product.get(), HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    @PutMapping("/{id_store}/products/{id_product}")
    public ResponseEntity<Product> updateStoreProduct(@PathVariable("id_store") @NotNull int id_store,
                                                      @PathVariable("id_product") @NotNull int id_product,
                                                      @RequestBody ProductVO productVO) {

        Optional<Store> store = storeService.findById(id_store);
        Optional<Product> product = productService.findById(id_product);
        if(store.isPresent()) {
            if(product.isPresent()) {
                product.get().setName(productVO.getName());
                product.get().setStock(productVO.getStock());

                return new ResponseEntity<>(productService.update(product.get()), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id_store}/products/{id_product}")
    public HttpStatus deleteStoreProduct(@PathVariable("id_store") @NotNull int id_store,
                                         @PathVariable("id_product") @NotNull int id_product){

        if(storeService.findById(id_store).isPresent() && productService.findById(id_product).isPresent()){

            productService.deleteById(id_product);

            return HttpStatus.OK;
        }
        return HttpStatus.BAD_REQUEST;
    }

    @PostMapping("/{id_store}/employees")
    public ResponseEntity<Employee> createStoreEmployee(@PathVariable("id_store") @NotNull int id_store,
                                                        @RequestBody EmployeeVO employeeVO) {

        Optional<Store> store = storeService.findById(id_store);
        if(store.isPresent()) {
            Employee employee = new Employee();
            employee.setId_employee(employeeVO.getId_employee());
            employee.setName(employeeVO.getName());
            employee.setPhone(employeeVO.getPhone());
            employee.setStore(store.get());

            return new ResponseEntity<>(employeeService.create(employee), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id_store}/employees")
    public ResponseEntity<List<Employee>> getStoreEmployees(@PathVariable("id_store") @NotNull int id_store) {

        if(storeService.findById(id_store).isPresent()){
            return new ResponseEntity<>(employeeService.findByStoreId(id_store), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id_store}/employees/{id_employee}")
    public ResponseEntity<Employee> findStoreEmployeeById(@PathVariable("id_store") @NotNull int id_store,
                                                          @PathVariable("id_employee") @NotNull int id_employee) {

        if(storeService.findById(id_store).isPresent()){
            Optional<Employee> employee = employeeService.findById(id_employee);
            if(employee.isPresent()){
                return new ResponseEntity<>(employee.get(), HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id_store}/employees/{id_employee}")
    public ResponseEntity<Employee> updateStoreEmployee(@PathVariable("id_store") @NotNull int id_store,
                                                        @PathVariable("id_employee") @NotNull int id_employee,
                                                        @RequestBody EmployeeVO employeeVO) {

        Optional<Store> store = storeService.findById(id_store);
        Optional<Employee> employee = employeeService.findById(id_employee);
        if(store.isPresent()) {
            if(employee.isPresent()) {
                employee.get().setName(employeeVO.getName());
                employee.get().setPhone(employeeVO.getPhone());

                return new ResponseEntity<>(employeeService.update(employee.get()), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id_store}/employees/{id_employee}")
    public HttpStatus deleteStoreEmployee(@PathVariable("id_store") @NotNull int id_store,
                                         @PathVariable("id_employee") @NotNull int id_employee){

        if(storeService.findById(id_store).isPresent() && employeeService.findById(id_employee).isPresent()){

            employeeService.deleteById(id_employee);

            return HttpStatus.OK;
        }
        return HttpStatus.BAD_REQUEST;
    }

    @PostMapping("/{id_store}/employees/{id_employee}/address")
    public ResponseEntity<Address> createStoreEmployeeAddress(@PathVariable("id_store") @NotNull int id_store,
                                                              @PathVariable("id_employee") @NotNull int id_employee,
                                                              @RequestBody AddressVO addressVO) {

        if(storeService.findById(id_store).isPresent()) {
            Optional<Employee> employee = employeeService.findById(id_employee);
            if(employee.isPresent()){
                if(addressService.findByEmployeeId(id_employee)) {

                    Address address = new Address();
                    address.setId_address(addressVO.getId_address());
                    address.setCountry(addressVO.getCountry());
                    address.setState(addressVO.getState());
                    address.setCity(addressVO.getCity());
                    address.setStreet(addressVO.getStreet());
                    address.setPostalCode(addressVO.getPostalCode());
                    address.setEmployee(employee.get());

                    return new ResponseEntity<>(addressService.create(address), HttpStatus.CREATED);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id_store}/employees/{id_employee}/address")
    public ResponseEntity<Address> getStoreEmployeesAddress(@PathVariable("id_store") @NotNull int id_store,
                                                   @PathVariable("id_employee") @NotNull int id_employee) {

        if(storeService.findById(id_store).isPresent() && employeeService.findById(id_employee).isPresent()){
            return new ResponseEntity<>(addressService.findByEmployeeIdObj(id_employee), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id_store}/employees/{id_employee}/address/{id_address}")
    public ResponseEntity<Address> findStoreEmployeeAddress(@PathVariable("id_store") @NotNull int id_store,
                                                          @PathVariable("id_employee") @NotNull int id_employee,
                                                          @PathVariable("id_address") @NotNull int id_address) {

        if(storeService.findById(id_store).isPresent()){
            if(employeeService.findById(id_employee).isPresent()){
                Optional<Address> address = addressService.findById(id_address);
                if(address.isPresent()) {
                    return new ResponseEntity<>(address.get(), HttpStatus.OK);
                }
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id_store}/employees/{id_employee}/address/{id_address}")
    public ResponseEntity<Address> updateStoreEmployeeAddress(@PathVariable("id_store") @NotNull int id_store,
                                                              @PathVariable("id_employee") @NotNull int id_employee,
                                                              @PathVariable("id_address") @NotNull int id_address,
                                                              @RequestBody AddressVO addressVO) {

        Optional<Store> store = storeService.findById(id_store);
        Optional<Employee> employee = employeeService.findById(id_employee);
        Optional<Address> address = addressService.findById(id_address);
        if(store.isPresent()) {
            if(employee.isPresent()) {
                if(address.isPresent()) {
                    address.get().setCountry(addressVO.getCountry());
                    address.get().setState(addressVO.getState());
                    address.get().setCity(addressVO.getCity());
                    address.get().setStreet(addressVO.getStreet());
                    address.get().setPostalCode(addressVO.getPostalCode());

                    return new ResponseEntity<>(addressService.update(address.get()), HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id_store}/employees/{id_employee}/address/{id_address}")
    public HttpStatus deleteStoreEmployeeAddress(@PathVariable("id_store") @NotNull int id_store,
                                                 @PathVariable("id_employee") @NotNull int id_employee,
                                                 @PathVariable("id_address") @NotNull int id_address){

        if(storeService.findById(id_store).isPresent() && employeeService.findById(id_employee).isPresent()
                && addressService.findById(id_address).isPresent()){

            addressService.deleteById(id_address);

            return HttpStatus.OK;
        }
        return HttpStatus.BAD_REQUEST;
    }


}
