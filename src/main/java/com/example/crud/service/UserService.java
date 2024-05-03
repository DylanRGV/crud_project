package com.example.crud.service;

import com.example.crud.repository.UserRepository;
import com.example.crud.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
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

    public void saveOrUpdate(User User){
        userRepository.save(User);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}


