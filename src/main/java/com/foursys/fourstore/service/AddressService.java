package com.foursys.fourstore.service;

import com.foursys.fourstore.dto.AddressDTO;
import com.foursys.fourstore.exceptions.EntityNotFoundException;
import com.foursys.fourstore.model.Address;
import com.foursys.fourstore.model.User;
import com.foursys.fourstore.repository.AddressRepository;
import com.foursys.fourstore.repository.UserRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ModelMapper modelMapper;
    
    
    
    public AddressDTO saveAddress(Long userId, Address address) {
        
        address.setUser(userRepository.findById(userId).orElseThrow(() -> {
            return new EntityNotFoundException("Não existe um usuário com o id informado");
        }));

        address = addressRepository.save(address);
        return modelMapper.map(address, AddressDTO.class);
    
    }
    
    

    public List<AddressDTO> findAllAddress() {
        return addressRepository.findAll().stream().map(address -> new AddressDTO()).toList();
    }


    public AddressDTO findById(Long id) {
        Address address = addressRepository.findById(id).orElseThrow(() -> {
            return new EntityNotFoundException("Não existe um endereço com o id informado");
        });

        return modelMapper.map(address, AddressDTO.class);
    }


    public List<AddressDTO> findByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> {
            return new EntityNotFoundException("Não existe um usuário com o id informado");
        });

        return addressRepository.findByUser(user).stream().map(address -> new AddressDTO()).toList();
    }


    public AddressDTO update(Long id, Address address) {
       address = addressRepository.findById(id).orElseThrow(() -> {
            return new EntityNotFoundException("Não existe um endereço com o id informado");
        });
        
        address.setId(id);
        address = addressRepository.save(address);
        return modelMapper.map(address, AddressDTO.class);
    }


    public void delete(Long id) {
        this.findById(id);
        addressRepository.deleteById(id);
    }
}