package com.foursys.fourstore.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foursys.fourstore.dto.UserDTO;
import com.foursys.fourstore.exceptions.EntityNotFoundException;
import com.foursys.fourstore.exceptions.UnreportedEssentialFieldException;
import com.foursys.fourstore.model.User;
import com.foursys.fourstore.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    public UserDTO findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> {
        	return new EntityNotFoundException("Não existe um endereço com o id informado");
        });
        
        return modelMapper.map(user, UserDTO.class);
    }

    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(user -> new UserDTO()).toList();
    }

    public UserDTO save(User user) {

        if(user.getFullName() == null) throw new UnreportedEssentialFieldException("Nome completo não informado");
        user.setFullName(user.getFullName());

        if(user.getEmail() == null) throw new UnreportedEssentialFieldException("Email não informado");
        user.setEmail(user.getEmail());

        if(user.getCpf() == null) throw new UnreportedEssentialFieldException("CPF não informado");
        user.setCpf(user.getCpf());

        if(user.getPassword() == null) throw new UnreportedEssentialFieldException("Senha não informada");
        user.setPassword(user.getPassword());

        user = userRepository.save(user);
        return modelMapper.map(user, UserDTO.class);
    }

    public UserDTO update(Long id, User user) {
        user = userRepository.findById(id).orElseThrow(() -> {
            return new EntityNotFoundException("Não existe um usuário com o id informado");
        });

        if(user.getFullName() != null) user.setFullName(user.getFullName());

        if(user.getEmail() != null) user.setEmail(user.getEmail());

        if(user.getCpf() != null) user.setCpf(user.getCpf());

        if(user.getPassword() != null) user.setPassword(user.getPassword());

        user.setId(id);
        user = userRepository.save(user);
        return modelMapper.map(user, UserDTO.class);
    }

    public void delete(Long id) {
        userRepository.delete(userRepository.findById(id).orElseThrow(() -> {
            return new EntityNotFoundException("Não existe um usuário com o id informado");
        }));
    }
}












