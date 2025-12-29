package com.user.controller;

import com.user.dto.CreateOrderRequest;
import com.user.dto.OrderResponseDto;
import com.user.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@Valid @RequestBody CreateOrderRequest request) {
        String orderId = service.createOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("orderId", orderId, "status", "CREATED"));
    }

    @GetMapping("order/{orderId}")
    public ResponseEntity<OrderResponseDto> getOrder(@PathVariable Long orderId) {
        OrderResponseDto dto = service.getOrder(orderId);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    @GetMapping("orderByCustomerId")
    public ResponseEntity<List<OrderResponseDto>> orderList(@RequestParam(required = false, defaultValue = "0") Long customerId) {
        return ResponseEntity.ok(service.orderList(customerId));
    }

}
