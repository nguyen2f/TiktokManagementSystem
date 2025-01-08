package com.ndc.tiktokmanagement.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "deliveryOrder")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerId;
    private String customerName;
    private String customerPhoneNumber;

    private Long productId;
    private int orderQuantity;

    private Long promotionId;
    private double totalAmount;

    private LocalDate orderDate;
    private LocalDate deliverDate;
    private String status;

    private Long complaintId;
}
