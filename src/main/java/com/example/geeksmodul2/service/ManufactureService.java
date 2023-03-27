package com.example.geeksmodul2.service;

import com.example.geeksmodul2.model.Manufacture;
import com.example.geeksmodul2.repository.ManufactureRepository;
import com.example.geeksmodul2.repository.SouvenirRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ManufactureService {
    ManufactureRepository manufactureRepository;

    public List<Manufacture> getManufactures() {
        return manufactureRepository.findAll();
    }

    public Manufacture getManufacture(int id) {
        return manufactureRepository.findById(id).orElse(new Manufacture());
    }
}
