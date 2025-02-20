package com.eletronic.eletronic.controller;

import com.eletronic.eletronic.models.producteletronic.ProductEletronicDTO;
import com.eletronic.eletronic.models.producteletronic.ProductEletronicEntity;
import com.eletronic.eletronic.service.ProductEletronicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
public class ProductEletronicController {

    @Autowired
    private  ProductEletronicService service;

    @GetMapping("/eletronic")
    public ResponseEntity<List<ProductEletronicEntity>> getAllProductsEletronics(){
        List<ProductEletronicEntity> eletronics = this.service.getAllProduct();

        List<ProductEletronicEntity> eletronicsNotDeleted = eletronics.stream()
                .filter(eletronic -> !eletronic.isDeleted())
                .toList();

        return ResponseEntity.ok().body(eletronicsNotDeleted);
    }

    @GetMapping("/eletronic/{id}")
    public ResponseEntity<ProductEletronicEntity> getProductById(@PathVariable("id") Long id) {
        ProductEletronicEntity eletronic = this.service.getProductFindById(id);

        return ResponseEntity.ok().body(eletronic);

    }

    @PostMapping("/eletronic")
    public ResponseEntity<ProductEletronicEntity> postProductEntity(@RequestBody ProductEletronicDTO data){


        ProductEletronicEntity eletronic = data.transformDtoForProductEletronicEntity();

        ProductEletronicEntity eletronicSaved = this.service.create(eletronic);

        return ResponseEntity.status(HttpStatus.CREATED).body(eletronicSaved);
    }

    @PutMapping("/eletronic/{id}")
    public ResponseEntity<ProductEletronicEntity> putProductEletronic(@PathVariable("id") Long id, @RequestBody ProductEletronicDTO  data) {


        ProductEletronicEntity eletronic = data.transformDtoForProductEletronicEntity();

        ProductEletronicEntity productEletronicSaved = this.service.update(id, eletronic);


        return ResponseEntity.status(HttpStatus.OK).body(productEletronicSaved);

    }

    @DeleteMapping("/eletronic/{id}")
    public void deleteProductEletronic(@PathVariable("id") Long id) {

        ProductEletronicEntity eletronic = this.service.getProductFindById(id);
        this.service.deleteProduct(eletronic.getId());

    }
}
