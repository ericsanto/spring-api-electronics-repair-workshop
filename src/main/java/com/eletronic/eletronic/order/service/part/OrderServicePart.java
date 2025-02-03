package com.eletronic.eletronic.order.service.part;


import com.eletronic.eletronic.models.spareparts.SparePartsEntity;
import com.eletronic.eletronic.service.ProductEletronicService;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_service_part")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderServicePart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_service_id", nullable = false)
    private ProductEletronicService.OrderService orderService;

    @ManyToOne
    @JoinColumn(name = "spare_part_id", nullable = false)
    private SparePartsEntity sparePart;

    @Column(name = "quantity")
    @Positive
    private Integer quantity;

}
