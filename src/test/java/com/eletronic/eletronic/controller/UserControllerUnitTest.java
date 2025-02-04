package com.eletronic.eletronic.controller;

import com.eletronic.eletronic.models.user.UserDto;
import com.eletronic.eletronic.service.UserService;
import org.apache.catalina.User;
import org.junit.jupiter.api.extension.ExtendWith;
import com.eletronic.eletronic.models.user.UserEntity;
import com.eletronic.eletronic.service.ProductEletronicService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerUnitTest {

    @Mock
    private UserService service;

    @InjectMocks
    private UserController controller;


    private UserEntity userEntity;

    private UserEntity user1;

    private UserEntity user2;

    @BeforeEach
    public void setUp() {


        user1 = new UserEntity();
        user1.setName("eruc");
        user1.setEmail("eric@mail.com");
        user1.setTelephone("7999173678");
        user1.setAddress("ruaA");
        user1.setId(1L);



        user2 = new UserEntity();
        user2.setName("eric");
        user2.setEmail("eric2@mail.com");
        user2.setTelephone("7999173678");
        user2.setAddress("ruaA");

    }


    @Test
    public void getAllUser() {

        when(service.getAllUser()).thenReturn(Arrays.asList(user1, user2));

        ResponseEntity<List<UserEntity>> response = controller.getUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("eruc", response.getBody().get(0).getName());
        assertEquals("eric", response.getBody().get(1).getName());
        assertEquals(2, response.getBody().size());

    }

    @Test
    public void getUserFindById() {

        when(service.getUserFindById(1L)).thenReturn(user1);

        ResponseEntity<UserEntity> response = controller.getUserById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("eruc", response.getBody().getName());
    }

    @Test
    public void createUser() {

        UserDto userDto = new UserDto("eric", "8484849", "eric@mail.com", "dgrrgrg");

        UserEntity userEntity = userDto.tranformForUserEntity();

        when(service.createUser(any(UserEntity.class))).thenReturn(userEntity);

        ResponseEntity<UserEntity> response = controller.postUser(userDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("eric", response.getBody().getName());


    }

    @Test
    public void putUser() {

        UserDto userDto = new UserDto("ERIC", "89959569", "teste@mail.com", "dfjfjdfjfdj");

        UserEntity user = userDto.tranformForUserEntity();

        when(service.updateUser(eq(1L), any(UserEntity.class))).thenReturn(user);

        ResponseEntity<UserEntity> response = controller.putUser(1L, userDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("ERIC", response.getBody().getName());

        verify(service, times(1)).updateUser(eq(1L), any(UserEntity.class));
    }

}

