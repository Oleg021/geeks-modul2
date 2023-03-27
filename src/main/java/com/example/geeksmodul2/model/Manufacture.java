package com.example.geeksmodul2.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "manufacture")
public class Manufacture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id",unique=true, nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = Integer.MAX_VALUE)
    private String name;

    @Column(name = "country", nullable = false, length = Integer.MAX_VALUE)
    private String country;

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "manufacture_id")
    private Set<Souvenir> souvenirs = new LinkedHashSet<>();

}