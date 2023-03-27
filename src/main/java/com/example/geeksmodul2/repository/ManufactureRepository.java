package com.example.geeksmodul2.repository;

import com.example.geeksmodul2.model.Manufacture;
import com.example.geeksmodul2.model.Souvenir;
import org.springframework.data.domain.Sort;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ManufactureRepository extends JpaRepository<Manufacture, Integer> {

    List<Manufacture> findBySouvenirs_PriceLessThan(Double price);



    List<Manufacture> findBySouvenirs_NameContainsAndSouvenirs_Date(String name, LocalDate date);


}