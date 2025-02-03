package com.eletronic.eletronic.repository;

import com.eletronic.eletronic.service.ProductEletronicService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderServiceRepository extends JpaRepository<ProductEletronicService.OrderService, Long> {
}
