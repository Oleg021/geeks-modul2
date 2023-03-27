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

    public Set<LocalDate> getLsitDate(List<Souvenir> souvenirs) {
        Set<LocalDate> localDates = new HashSet<>();
        for (Souvenir elem : souvenirs) {
            localDates.add(elem.getDate());
        }
        return localDates;
    }

    public Map<Integer, List<Souvenir>> getMapOfDates(List<Souvenir> souvenirs) {
        Map<Integer, List<Souvenir>> souvenirMap = new HashMap<>();
        for (Souvenir elem : souvenirs) {
            souvenirMap.put(elem.getDate().getYear(), souvenirs);
        }
        return souvenirMap;
    }

    public Map<Integer, Map<Integer, String>> getMapsOfDates(List<Souvenir> souvenirs) {
        Map<Integer, Map<Integer, String>> souvenirMap = new HashMap<>();
        Map<Integer, String> map = new HashMap<>();
        for (Souvenir elem : souvenirs) {
            map.put(elem.getId(), elem.getName());
            souvenirMap.put(elem.getDate().getYear(), map);
        }
        return souvenirMap;
    }


}
