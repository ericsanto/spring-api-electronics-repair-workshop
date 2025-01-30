package com.eletronic.eletronic.oder.service;


import com.eletronic.eletronic.models.producteletronic.ProductEletronicEntity;
import com.eletronic.eletronic.order.service.part.OrderServicePart;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name= "order_service")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderService {


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
