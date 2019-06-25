package com.globant.bootcamp.shop.endpoint;

import com.globant.bootcamp.shop.bussiness.repository.EmployeeRepository;
import com.globant.bootcamp.shop.bussiness.repository.ProductRepository;
import com.globant.bootcamp.shop.model.Employee;
import com.globant.bootcamp.shop.model.Product;
import com.globant.bootcamp.shop.resources.vo.EmployeeVO;
import com.globant.bootcamp.shop.resources.vo.ProductVO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeEndPoint {

    private final EmployeeRepository employeeRepository;

    public EmployeeEndPoint(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/total")
    public long getTotalProducts() {
        return employeeRepository.count();
    }

    @GetMapping("/{id_employee}")
    public Optional<Employee> findProductById(@PathVariable("id_employee") @NotNull int id_employee) {
        return employeeRepository.findById(id_employee);
    }

    @PostMapping
    public ResponseEntity<Employee> createProduct(@RequestBody EmployeeVO employeeVO) {
        Employee employee = new Employee();
        employee.setId_employee(employeeVO.getId_employee());
        employee.setName(employeeVO.getName());
        employee.setAddress(employeeVO.getAddress());
        employee.setPhone(employeeVO.getPhone());

        return new ResponseEntity<>(this.employeeRepository.save(employee), HttpStatus.CREATED);
    }

    @PutMapping("/{id_employee}")
    @ApiOperation(value = "Update employee", notes = "Service to update a employee")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Employee updated success"),
            @ApiResponse(code = 404, message = "Employee not found") })
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id_employee") int id_employee,
                                                 EmployeeVO employeeVO) {

        Employee employee = this.employeeRepository.findByIdentification(id_employee);

        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } else {
            employee.setName(employeeVO.getName());
            employee.setAddress(employeeVO.getAddress());
            employee.setPhone(employeeVO.getPhone());
        }

        return new ResponseEntity<>(this.employeeRepository.save(employee), HttpStatus.OK);
    }


}
