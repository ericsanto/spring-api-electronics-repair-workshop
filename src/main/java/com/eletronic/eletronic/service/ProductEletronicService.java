package com.eletronic.eletronic.service;

import com.eletronic.eletronic.models.producteletronic.ProductEletronicEntity;
import com.eletronic.eletronic.order.service.part.OrderServicePart;
import com.eletronic.eletronic.repository.ProductEletronicRepository;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    public  ProductEletronicEntity update(Long id, ProductEletronicEntity data){
        ProductEletronicEntity newEletronic = this.getProductFindById(id);

        newEletronic.setTypeEletronic(data.getTypeEletronic());
        newEletronic.setNumberOfSerie(data.getNumberOfSerie());
        newEletronic.setModel(data.getModel());
        newEletronic.setMark(data.getMark());

        this.repository.save(newEletronic);

        return newEletronic;

    }

    public void deleteProduct(Long id) {
        ProductEletronicEntity eletronic = this.getProductFindById(id);

        eletronic.setDeleted(true);

        this.repository.save(eletronic);

    }

    @Entity
    @Table(name= "order_service")
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class OrderService {


        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;


        @Column(name = "description")
        private String description;

        @Column(name = "date_open")
        @NotNull
        @NotBlank
        private LocalDateTime dateOpen;

        @Column(name = "date_close")
        private LocalDateTime dateClose;

        @OneToMany(mappedBy = "orderService", cascade = CascadeType.ALL , orphanRemoval = true)
        private List<OrderServicePart> orderServicePart;

    }
}

