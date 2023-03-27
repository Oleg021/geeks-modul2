package com.example.geeksmodul2.service;

import com.example.geeksmodul2.model.Manufacture;
import com.example.geeksmodul2.model.Souvenir;
import com.example.geeksmodul2.repository.SouvenirRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@AllArgsConstructor
public class SouvenirService {
    SouvenirRepository souvenirRepository;

    public void addSouvenir(Manufacture manufacture, String name, int requisites, LocalDate date, double price) {
        Souvenir souvenir = new Souvenir();
        souvenir.setName(name);
        souvenir.setRequisites(requisites);
        souvenir.setDate(date);
        souvenir.setPrice(price);
        souvenir.setManufacture(manufacture);
        souvenirRepository.save(souvenir);
    }

}
