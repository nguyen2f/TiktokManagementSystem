package com.ndc.tiktokmanagement.controller;

import com.ndc.tiktokmanagement.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class OrderStatisticsController {
    private final OrderService orderService;

    public OrderStatisticsController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/order-statistics")
    public ResponseEntity<Map<String, Long>> getOrderStatistics() {
        Map<String, Long> statistics = orderService.getOrderStatistics();
        return ResponseEntity.ok(statistics);
    }
    @GetMapping("/daily-revenue")
    public List<Map<String, Object>> getDailyRevenue() {
        return orderService.getDailyRevenue();
    }
}

