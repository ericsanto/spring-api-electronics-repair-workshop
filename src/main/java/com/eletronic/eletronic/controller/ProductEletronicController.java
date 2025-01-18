package com.eletronic.eletronic.controller;

import com.eletronic.eletronic.producteletronic.ProductEletronicEntity;
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

        return ResponseEntity.ok().body(eletronics);
    }

    @GetMapping("/eletronic/{id}")
    public ResponseEntity<ProductEletronicEntity> getProductById(@PathVariable("id") Long id) {
        ProductEletronicEntity eletronic = this.service.getProductFindById(id);

        return ResponseEntity.ok().body(eletronic);

    }

    @PostMapping("/eletronic")
    public ResponseEntity<ProductEletronicEntity> postProductEntity(@RequestBody ProductEletronicEntity data){
        ProductEletronicEntity eletronic = this.service.create(data);

        return ResponseEntity.status(HttpStatus.CREATED).body(eletronic);
    }

    @PutMapping("/eletronic")
    public ResponseEntity<ProductEletronicEntity> putProductEletronic(@RequestBody ProductEletronicEntity  data) {
        ProductEletronicEntity eletronic = this.service.update(data);

        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @DeleteMapping("/eletronic/{id}")
    public ResponseEntity<ProductEletronicEntity>  deleteProductEletronic(@PathVariable("id") Long id) {
        this.service.deleteProduct(id);

        return ResponseEntity.ok().build();
    }
}
