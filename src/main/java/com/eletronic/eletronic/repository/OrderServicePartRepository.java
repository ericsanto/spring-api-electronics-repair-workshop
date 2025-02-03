package com.eletronic.eletronic.repository;

import com.eletronic.eletronic.order.service.part.OrderServicePart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderServicePartRepository extends JpaRepository<OrderServicePart, Long> {
}
