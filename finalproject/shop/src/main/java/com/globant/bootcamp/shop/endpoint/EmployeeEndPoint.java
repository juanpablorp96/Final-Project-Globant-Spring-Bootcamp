package com.globant.bootcamp.shop.endpoint;

import com.globant.bootcamp.shop.bussiness.service.EmployeeService;
import com.globant.bootcamp.shop.model.Employee;
import com.globant.bootcamp.shop.resources.vo.EmployeeVO;
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
@RequestMapping("/employees")
public class EmployeeEndPoint {

    private final EmployeeService employeeService;

    public EmployeeEndPoint(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getEmployees() {
        return employeeService.findAll();
    }

    @GetMapping("/total")
    public long getTotalProducts() {
        return employeeService.count();
    }

    @GetMapping("/{id_employee}")
    public Optional<Employee> findProductById(@PathVariable("id_employee") @NotNull int id_employee) {
        return employeeService.findById(id_employee);
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody EmployeeVO employeeVO) {
        Employee employee = new Employee();
        employee.setId_employee(employeeVO.getId_employee());
        employee.setName(employeeVO.getName());
        employee.setPhone(employeeVO.getPhone());

        return new ResponseEntity<>(this.employeeService.create(employee), HttpStatus.CREATED);
    }

    @PutMapping("/{id_employee}")
    @ApiOperation(value = "Update employee", notes = "Service to update a employee")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Employee updated success"),
            @ApiResponse(code = 404, message = "Employee not found") })
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id_employee") @NotNull int id_employee,
                                                   @RequestBody EmployeeVO employeeVO) {

        Optional<Employee> employee = employeeService.findById(id_employee);

        if (employee.isPresent()) {
            employee.get().setName(employeeVO.getName());
            employee.get().setPhone(employeeVO.getPhone());

            return new ResponseEntity<>(employeeService.update(employee.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/{id_employee}")
    public HttpStatus deleteEmployee(@PathVariable("id_employee") @NotNull int id_employee){
        if(employeeService.findById(id_employee).isPresent()){
            employeeService.deleteById(id_employee);

            return HttpStatus.OK;
        }
        return HttpStatus.BAD_REQUEST;
    }


}
