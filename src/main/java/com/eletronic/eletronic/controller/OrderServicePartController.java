package com.eletronic.eletronic.controller;

import com.eletronic.eletronic.models.spareparts.SparePartsEntity;
import com.eletronic.eletronic.order.service.part.OrderServicePart;
import com.eletronic.eletronic.service.OrderServicePartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
public class OrderServicePartController {

    @Autowired
    private OrderServicePartService service;

    @GetMapping("/order-service-spare-part")
    public ResponseEntity<List<OrderServicePart>> getAllOrderServiceParts() {
        List<OrderServicePart> orderServicesParts = this.service.getAllOrderServiceParts();
        return ResponseEntity.ok().body(orderServicesParts);
    }

    @GetMapping("/order-service-spare-part/{id}")
    public ResponseEntity<OrderServicePart> getOrderServicePart(@PathVariable("id") Long id) {
        OrderServicePart orderServicesPart = this.service.getOrderServicePartFindById(id);

        return ResponseEntity.ok().body(orderServicesPart);
    }

    @PostMapping("/order-service-spare-part")
    public ResponseEntity<OrderServicePart> postOrderServicePart(@RequestBody  OrderServicePart data) {
        OrderServicePart orderServicesPart = this.service.postOrderServicePart(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderServicesPart);
    }

    @PutMapping("/order-service-spare-part/{id}")
    public ResponseEntity<OrderServicePart> putSparePart(@PathVariable("id") Long id, @RequestBody OrderServicePart data) {
        OrderServicePart  orderServicesPart = this.service.putOrderServicePart(id, data);

        return ResponseEntity.ok().body(orderServicesPart);
    }


}
