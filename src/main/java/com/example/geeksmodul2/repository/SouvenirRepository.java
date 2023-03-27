package com.example.geeksmodul2.repository;

import com.example.geeksmodul2.model.Souvenir;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;


public interface SouvenirRepository extends JpaRepository<Souvenir, Integer> {
    List<Souvenir> findByManufacture_CountryContains(String country);

    List<Souvenir> findByManufacture_NameContains(String name);

    @Query(value = "select s from  Souvenir s order by s.date")
    List<Souvenir> sortByDate();


    @Query(value = "select s from Souvenir s where year(s.date) = ?1")
    List<Souvenir> showByYear(int year);




}