package com.eletronic.eletronic.service;

import com.eletronic.eletronic.order.service.part.OrderServicePart;
import com.eletronic.eletronic.repository.OrderServicePartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServicePartService {

    @Autowired
    private OrderServicePartRepository repository;

    public List<OrderServicePart> getAllOrderServiceParts() {

        List<OrderServicePart> orderServiceParts = this.repository.findAll();

        return orderServiceParts;
    }

    public OrderServicePart getOrderServicePartFindById(Long id) {

        Optional<OrderServicePart> orderServicePart = this.repository.findById(id);

        return orderServicePart.orElseThrow(() -> new RuntimeException("Does not exist order service with id"));
    }

    public OrderServicePart postOrderServicePart(OrderServicePart data) {

        this.repository.save(data);

        return data;
    }

    public OrderServicePart putOrderServicePart(Long id, OrderServicePart data) {
        OrderServicePart orderServicePart = this.getOrderServicePartFindById(id);

        orderServicePart.setOrderService(data.getOrderService());
        orderServicePart.setSparePart(data.getSparePart());
        orderServicePart.setQuantity(data.getQuantity());

        this.repository.save(orderServicePart);

        return orderServicePart;

    }

}
