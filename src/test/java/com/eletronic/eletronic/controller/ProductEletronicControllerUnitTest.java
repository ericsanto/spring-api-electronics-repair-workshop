package com.eletronic.eletronic.controller;

import com.eletronic.eletronic.models.producteletronic.Mark;
import com.eletronic.eletronic.models.producteletronic.ProductEletronicDTO;
import com.eletronic.eletronic.models.producteletronic.ProductEletronicEntity;
import com.eletronic.eletronic.models.producteletronic.TypeEletronic;
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
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductEletronicControllerUnitTest {

    @Mock
    private ProductEletronicService service;

    @InjectMocks
    private ProductEletronicController controller;


    private ProductEletronicEntity productEletronic1;

    private ProductEletronicEntity productEletronic2;

    private UserEntity user;

    @BeforeEach
    public void setUp() {

        user = new UserEntity();
        user.setName("teste");
        user.setTelephone("656767");
        user.setId(1L);
        user.setEmail("teste@mail.com");
        user.setAddress("ruaa");
        user.setDeleted(false);

        productEletronic1 = new ProductEletronicEntity();
        productEletronic1.setClient(user);
        productEletronic1.setModel("234848399");
        productEletronic1.setMark(Mark.SAMSUNG);
        productEletronic1.setNumberOfSerie("3434343");
        productEletronic1.setTypeEletronic(TypeEletronic.TV);
        productEletronic1.setId(1L);

    }

    @Test
    public void getAllProductEletronic() {

            when(service.getAllProduct()).thenReturn(Arrays.asList(productEletronic1));

            ResponseEntity<List<ProductEletronicEntity>> response = controller.getAllProductsEletronics();

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(1, response.getBody().size());
            assertEquals("234848399", response.getBody().get(0).getModel());

    }

    @Test
    public void getProductFindById() {

        when(service.getProductFindById(1L)).thenReturn(productEletronic1);

        ResponseEntity<ProductEletronicEntity> response = controller.getProductById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("234848399", response.getBody().getModel());

    }

    @Test
    public void postProductEletronic() {

        ProductEletronicDTO productEletronicDTO = new ProductEletronicDTO(TypeEletronic.TV, Mark.SAMSUNG, "848fhfidfdf", "39e4849", user);

        ProductEletronicEntity productEletronic = productEletronicDTO.transformDtoForProductEletronicEntity();

        when(service.create(any(ProductEletronicEntity.class))).thenReturn(productEletronic);

        ResponseEntity<ProductEletronicEntity> response = controller.postProductEntity(productEletronicDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(TypeEletronic.TV, response.getBody().getTypeEletronic());
    }

    @Test
    public void putProductEletronic() {

        ProductEletronicDTO productEletronicDTO = new ProductEletronicDTO(TypeEletronic.TV, Mark.SAMSUNG, "848fhfidfdf", "39e4849", user);

        ProductEletronicEntity productEletronic = productEletronicDTO.transformDtoForProductEletronicEntity();

        when(service.update(eq(1L), any(ProductEletronicEntity.class))).thenReturn(productEletronic);

        ResponseEntity<ProductEletronicEntity> response = controller.putProductEletronic(1L, productEletronicDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productEletronic.getMark(), response.getBody().getMark());

        verify(service, times(1)).update(eq(1L), any(ProductEletronicEntity.class));
    }

}

