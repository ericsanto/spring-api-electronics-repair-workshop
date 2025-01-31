package com.eletronic.eletronic.models.spareparts;


import com.eletronic.eletronic.order.service.part.OrderServicePart;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name= "spare_part")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SparePartsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotNull
    @NotBlank
    private String name;

    @Column(name = "model")
    @NotNull
    @NotBlank
    private String model;

    @Column(name = "unit_price")
    @NotNull
    private Integer unitPrice;

    @Column(name = "stock_quantity")
    @NotNull
    private Integer stockQuantity;

    @OneToMany(mappedBy = "sparePart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderServicePart> orderServicePart = new ArrayList<>();





}
