package com.ndc.tiktokmanagement.repository;

import com.ndc.tiktokmanagement.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAll();
    long countByStatus(String status);

    @Query("SELECT o.orderDate AS orderDate, SUM(o.totalAmount) AS totalRevenue " +
            "FROM Order o WHERE o.status = 'completed' " +
            "GROUP BY o.orderDate " +
            "ORDER BY o.orderDate ASC")
    List<Object[]> calculateDailyRevenue();
}