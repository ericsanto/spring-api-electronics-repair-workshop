package com.eletronic.eletronic.controller;

import com.eletronic.eletronic.models.user.UserDto;
import com.eletronic.eletronic.service.UserService;
import com.eletronic.eletronic.models.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
@Validated
public class UserController {

    @Autowired
    private UserService sevice;


    @GetMapping("/users")
    public ResponseEntity<List<UserEntity>> getUsers() {


        List<UserEntity> users = this.sevice.getAllUser();
        List<UserEntity> usersNotDeleted = users.stream()
                .filter(user -> !user.isDeleted()) // filter the elements with value user.isDeleted = false
                .toList(); // transform the elements of the stream in a list

        return  ResponseEntity.ok().body(usersNotDeleted);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable("id") Long id) {
        UserEntity user = this.sevice.getUserFindById(id);

        if (!user.isDeleted()){
            return ResponseEntity.ok().body(user);
        }

        return ResponseEntity.badRequest().build();


    }

    @PostMapping("/users")
    public ResponseEntity<UserEntity> postUser(@RequestBody UserDto data) {

        UserEntity user = data.tranformForUserEntity();

        UserEntity userSaved = this.sevice.createUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(userSaved);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserEntity> putUser(@PathVariable("id") Long id, @RequestBody UserDto data)  {


        UserEntity user = data.tranformForUserEntity();

        UserEntity userSaved = this.sevice.updateUser(id, user);

        return ResponseEntity.ok().body(userSaved);

    }

    @DeleteMapping("/users/{id}")
    public void deletUser(@PathVariable("id") Long id) {
        UserEntity user = this.sevice.getUserFindById(id);

        this.sevice.deleteUser(user.getId());

    }

}
