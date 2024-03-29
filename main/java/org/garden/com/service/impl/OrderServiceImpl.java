package org.garden.com.service.impl;

import lombok.RequiredArgsConstructor;
import org.garden.com.entity.Order;
import org.garden.com.enums.OrderStatus;
import org.garden.com.repository.OrderJpaRepository;
import org.garden.com.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderJpaRepository repository;

    @Override
    public Order create(Order order) {
        return repository.save(order);
    }

    @Override
    public List<Order> getAll() {
        return repository.findAll();
    }

    @Override
    public Order getStatus(Order order, OrderStatus status) {
        return repository.getReferenceById(order.getId());
    }
}
