package com.globant.bootcamp.shop.bussiness.service;

import com.globant.bootcamp.shop.bussiness.repository.AddressRepository;
import com.globant.bootcamp.shop.model.Address;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Optional<Address> findById(int id_address){
        return addressRepository.findById(id_address);
    }

    public Address findByIdentification(int id_address){
        return addressRepository.findByIdentification(id_address);
    }

    public boolean findByEmployeeId(int id_employee){
        if (addressRepository.findByEmployeeId(id_employee) != null) {
            return false;
        }
        return true;
    }

    public Address findByEmployeeIdObj(int id_employee){
        return addressRepository.findByEmployeeId(id_employee);
    }

    public List<Address> findAll(){
        return addressRepository.findAll();
    }

    public long count(){
        return addressRepository.count();
    }

    @Transactional
    public Address create(Address address){
        return addressRepository.save(address);
    }

    @Transactional
    public Address update(Address address){
        return addressRepository.save(address);
    }

    @Transactional
    public void deleteById(int id_address){
        addressRepository.deleteById(id_address);
    }


}
