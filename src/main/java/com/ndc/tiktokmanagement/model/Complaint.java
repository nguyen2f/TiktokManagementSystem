package com.ndc.tiktokmanagement.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int CustomerID;

    private int orderID;
    private String description;
    private String status;

    private int employeeID;

}
