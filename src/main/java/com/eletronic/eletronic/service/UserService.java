package com.eletronic.eletronic.service;

import com.eletronic.eletronic.repository.UserRepository;
import com.eletronic.eletronic.models.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public UserEntity getUserFindById(Long id) {
        Optional<UserEntity> user = this.repository.findById(id);

        return user.orElseThrow(() -> new RuntimeException("User not found with id"));

    }

    public List<UserEntity> getAllUser() {
        List<UserEntity> users = this.repository.findAll();
        return users;
    }

    public UserEntity createUser(UserEntity data) {

        UserEntity user = this.repository.save(data);

        return user;
    }

    public UserEntity updateUser(Long id, UserEntity data) {
        UserEntity user = this.getUserFindById(id);

        user.setEmail(data.getEmail());
        user.setTelephone(data.getTelephone());
        user.setName(data.getName());
        user.setAddress(data.getAddress());

        this.repository.save(user);
        return user;
    }

    public void deleteUser(Long id) {
        UserEntity user = this.getUserFindById(id);

        user.setDeleted(true);

        this.repository.save(user);


    }
}


