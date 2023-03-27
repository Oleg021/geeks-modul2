package com.example.geeksmodul2.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "souvenirs")
public class Souvenir {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = Integer.MAX_VALUE)
    private String name;

    @Column(name = "requisites", nullable = false)
    private Integer requisites;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "price", nullable = false)
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manufacture_id")
    private Manufacture manufacture;

}