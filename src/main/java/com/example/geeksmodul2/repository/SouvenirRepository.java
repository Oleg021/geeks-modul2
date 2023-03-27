package com.example.geeksmodul2.repository;

import com.example.geeksmodul2.model.Souvenir;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface SouvenirRepository extends JpaRepository<Souvenir, Integer> {
    List<Souvenir> findByManufacture_CountryContains(String country);

    List<Souvenir> findByManufacture_NameContains(String name);

    @Query(value = "select s from  Souvenir s order by s.date")
    List<Souvenir> sortByDate();

    @Query(value = "select year(s.date) as year, s.name from Souvenir s" +
            " group by year(s.date), s.name" +
            " order by year(s.date) asc")
    List<Souvenir> sortByYear();



}