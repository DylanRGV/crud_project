package com.example.crud.service;

import com.example.crud.repository.UserRepository;
import com.example.crud.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getUsers(){
        return userRepository.findAll();
    }
    public Optional<User> getUsers(Long id){
        return userRepository.findById(id);
    }

    public void saveOrUpdate(User user) {
        userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }


    public boolean registerNewUser(User user){
        try {
            if(userRepository.existsByUsername(user.getUsername())){
                return false;
            }
            userRepository.save(user);
            return true;
        } catch (DataIntegrityViolationException e) {
            System.err.println("Error al registrar usuario: " + e.getMessage());
            return false;
        }
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}


