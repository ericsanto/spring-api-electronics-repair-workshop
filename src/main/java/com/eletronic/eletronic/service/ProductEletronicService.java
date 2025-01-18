package com.eletronic.eletronic.service;

import com.eletronic.eletronic.producteletronic.ProductEletronicEntity;
import com.eletronic.eletronic.repository.ProductEletronicRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductEletronicService {

    @Autowired
    private ProductEletronicRepository repository;

    public ProductEletronicEntity getProductFindById(Long id) {
        Optional<ProductEletronicEntity> eletronic = this.repository.findById(id);
        
        return eletronic.orElseThrow(() -> new RuntimeException("User with id not found!"));



    }

    public List<ProductEletronicEntity> getAllProduct() {
        List<ProductEletronicEntity> eletronics = this.repository.findAll();

        return eletronics;
    }

    @Transactional
    public ProductEletronicEntity create(ProductEletronicEntity data) {
        ProductEletronicEntity eletronic = this.repository.save(data);
        return eletronic;

    }

    @Transactional
    public  ProductEletronicEntity update(ProductEletronicEntity data){
        ProductEletronicEntity newEletronic = this.getProductFindById(data.getId());

        newEletronic.setTypeEletronic(data.getTypeEletronic());
        newEletronic.setNumberOfSerie(data.getNumberOfSerie());
        newEletronic.setModel(data.getModel());
        newEletronic.setMark(data.getMark());

        this.repository.save(newEletronic);

        return newEletronic;

    }

    @Transactional
    public void deleteProduct(Long id) {
        ProductEletronicEntity eletronic = this.getProductFindById(id);

        eletronic.setDeleted(true);

    }

}

