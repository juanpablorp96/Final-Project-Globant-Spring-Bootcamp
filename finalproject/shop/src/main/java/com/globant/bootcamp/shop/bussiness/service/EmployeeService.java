package com.globant.bootcamp.shop.bussiness.service;

import com.globant.bootcamp.shop.bussiness.repository.EmployeeRepository;
import com.globant.bootcamp.shop.model.Employee;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Optional<Employee> findById(int id_employee){
        return employeeRepository.findById(id_employee);
    }

    public Employee findByIdentification(int id_employee){
        return employeeRepository.findByIdentification(id_employee);
    }

    public List<Employee> findByStoreId(int id_store){
        return employeeRepository.findByStoreId(id_store);
    }

    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    public long count(){
        return employeeRepository.count();
    }

    @Transactional
    public Employee create(Employee employee){
        return employeeRepository.save(employee);
    }

    @Transactional
    public Employee update(Employee employee){
        return employeeRepository.save(employee);
    }

    @Transactional
    public void deleteById(int id_employee){
        employeeRepository.deleteById(id_employee);
    }


}
