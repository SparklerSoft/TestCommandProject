package org.garden.com.controller;

import org.garden.com.converter.OrderMapper;
import org.garden.com.dto.OrderCreateDto;
import org.garden.com.dto.OrderDto;
import org.garden.com.entity.Order;
import org.garden.com.exceptions.OrderInvalidArgumentException;
import org.garden.com.exceptions.OrderNotFoundException;
import org.garden.com.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/orders")
public class OrderController {

    @Autowired
    private /*final*/ OrderService orderService;

    @Autowired
    private /*final*/ OrderMapper orderMapper;

//    public OrderController(OrderService orderService, OrderMapper orderMapper) {
//        this.orderService = orderService;
//        this.orderMapper = orderMapper;
//    }

    @GetMapping
    public ResponseEntity <List<OrderDto>> getAll() {
        try {
            List<Order> orders = orderService.getAll();
            List<OrderDto> orderDto = orders.stream()
                    .map(order -> orderMapper.orderToOrderDto(order))
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.OK).body(orderDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<OrderCreateDto> createOrder(@RequestBody OrderCreateDto dto) {

        try {
            dto.setCreatedAt(LocalDateTime.now());
            dto.setUpdatedAt(LocalDateTime.now());

            Order order = orderMapper.createOrderDtoToOrder(dto);
            orderService.create(order);
            OrderCreateDto createdOrderDto = orderMapper.orderToOrderCreateDto(order);

            return ResponseEntity.status(HttpStatus.CREATED).body(createdOrderDto);
        } catch (OrderInvalidArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

//        @ResponseStatus(HttpStatus.NO_CONTENT)
//        @ExceptionHandler
//        public String handleException (Exception e){
//            return handleException(new Exception("No content"));
//        }
//        @ResponseStatus(HttpStatus.BAD_REQUEST)
//        @ExceptionHandler({OrderInvalidArgumentException.class,
//                OrderNotFoundException.class})
//        public String handleInvalidArgumentException (Exception exception){
//            return exception.getMessage();
//        }
    }
