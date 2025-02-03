package com.eletronic.eletronic.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasSize;

import com.eletronic.eletronic.models.producteletronic.Mark;
import com.eletronic.eletronic.models.producteletronic.TypeEletronic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.eletronic.eletronic.models.producteletronic.ProductEletronicEntity;
import com.eletronic.eletronic.models.user.UserEntity;
import com.eletronic.eletronic.repository.ProductEletronicRepository;
import com.eletronic.eletronic.repository.UserRepository;

import java.util.Arrays;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ProductEletronicControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductEletronicRepository productEletronicRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {

        productEletronicRepository.deleteAll();
        userRepository.deleteAll();


        UserEntity user = new UserEntity();
        user.setAddress("rua");
        user.setTelephone("12345");
        user.setEmail("teste" + System.currentTimeMillis() + "@mail.com"); // E-mail único
        user.setName("teste");

        userRepository.save(user);


        ProductEletronicEntity product1 = new ProductEletronicEntity();
        product1.setTypeEletronic(TypeEletronic.TV);
        product1.setMark(Mark.SAMSUNG);
        product1.setModel("Model X");
        product1.setNumberOfSerie("12345");
        product1.setDeleted(false);
        product1.setClient(user);

        productEletronicRepository.save(product1);
    }

    @Test
    public void testGetAllProductsEletronics() throws Exception {
        mockMvc.perform(get("/api/eletronic"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1))) // Apenas 1 produto não deletado
                .andExpect(jsonPath("$[0].model").value("Model X")); // Verifica o modelo do produto
    }

    @Test
    public void testPostProductEletronics() throws Exception {

        UserEntity user = new UserEntity();
        user.setAddress("ruaa");
        user.setTelephone("123458");
        user.setEmail("teste" + System.currentTimeMillis() + "@email.com"); // E-mail único
        user.setName("teste1");

        userRepository.save(user);

        String productJson = String.format("""
        {
            "typeEletronic": "TV",
            "mark": "SAMSUNG",
            "model": "Model X",
            "numberOfSerie": "12345",
            "deleted": false,
            "client": {
                "id": %d
            }
        }
        """, user.getId());

        mockMvc.perform(post("/api/eletronic")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.model").value("Model X"))
                .andExpect(jsonPath("$.typeEletronic").value("TV"));
    }
}