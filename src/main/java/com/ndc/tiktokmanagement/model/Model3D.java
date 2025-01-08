package com.ndc.tiktokmanagement.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Model3D {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "product_id") // Khóa ngoại trỏ tới Customer
    private Product productID;
    private String name;
    private String data;
    private int width;
    private int height;
    private int depth;
    private String format;

}
