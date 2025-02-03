package com.eletronic.eletronic.service;

import com.eletronic.eletronic.repository.OrderServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class OrderServiceService {

    @Autowired
    private OrderServiceRepository repository;



    public List<ProductEletronicService.OrderService> getAllOrderService() {

        List<ProductEletronicService.OrderService> orderServices = this.repository.findAll();

        return orderServices;
    }

    public ProductEletronicService.OrderService getOrderServiceFindById(Long id) {

        Optional<ProductEletronicService.OrderService> orderService = this.repository.findById(id);

        return orderService.orElseThrow(() -> new RuntimeException("Does not exist order service with id"));
    }

    public ProductEletronicService.OrderService postOrderService(ProductEletronicService.OrderService data) {

        this.repository.save(data);

        return data;
    }

    public ProductEletronicService.OrderService putOrderService(ProductEletronicService.OrderService data, Long id) {
        ProductEletronicService.OrderService orderService = this.getOrderServiceFindById(id);

        orderService.setDescription(data.getDescription());
        orderService.setOrderServicePart(data.getOrderServicePart());
        orderService.setDateClose(data.getDateClose());

        this.repository.save(orderService);

        return orderService;

    }

}
