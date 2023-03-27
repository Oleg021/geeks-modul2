package com.example.geeksmodul2.controller;

import com.example.geeksmodul2.model.Manufacture;
import com.example.geeksmodul2.model.Souvenir;
import com.example.geeksmodul2.repository.SouvenirRepository;
import com.example.geeksmodul2.service.ManufactureService;
import com.example.geeksmodul2.service.SouvenirService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class SouvenirController {

    SouvenirRepository souvenirRepository;
    SouvenirService souvenirService;
    ManufactureService manufactureService;


    @GetMapping("/souvenirs")
    public String allSouvenirs(Model model) {
       List<Souvenir> souvenirs = souvenirRepository.findAll();
       model.addAttribute("souvenirs", souvenirs);
        return "souvenirs";
    }

    @GetMapping("edit_souvenir/{id}")
    public String editSouvenir(@PathVariable("id") int id, Model model) {
        Optional<Souvenir> souvenir = souvenirRepository.findById(id);
        if (souvenir.isEmpty()) {
            return "redirect:/souvenirs";
        }
        model.addAttribute("souvenir", souvenir.get());
        return "edit_souvenir";
    }

    @PostMapping("/update_souvenir")
    public String updateSouvenir(@RequestParam("id") int id,
                                 @RequestParam("name") String name,
                                 @RequestParam("requisites") int requisites,
                                 @RequestParam("date") LocalDate date,
                                 @RequestParam("price") double price) {
        Optional<Souvenir> souvenir = souvenirRepository.findById(id);
        if (souvenir.isEmpty()) {
            return "redirect:/souvenirs";
        }
        Souvenir s = souvenir.get();
        s.setName(name);
        s.setRequisites(requisites);
        s.setDate(date);
        s.setPrice(price);
        souvenirRepository.save(s);
        return "redirect:/souvenirs";
    }

    @GetMapping("/delete_souvenir/{id}")
    public String deleteSouvenir(@PathVariable("id") int id) {
        souvenirRepository.deleteById(id);
        return "redirect:/souvenirs";
    }


    @PostMapping ("/add_souvenir")
    public String addSouvenir(@RequestParam("manufacture_id") int mid,
                              @RequestParam("name") String name,
                              @RequestParam("requisites") int requisites,
                              @RequestParam("date") LocalDate date,
                              @RequestParam("price") double price) {
        Manufacture manufacture = manufactureService.getManufacture(mid);
        souvenirService.addSouvenir(manufacture, name, requisites, date, price);
        return "redirect:/manufacture_souvenirs/"+mid;
    }


    @GetMapping("/show_by_country")
    public String showByCountry(@RequestParam("country") String country, Model model) {
        List<Souvenir> souvenirs = souvenirRepository.findByManufacture_CountryContains(country);
        model.addAttribute("souvenirs", souvenirs);

        return "show_souvenirs_by_country";
    }

    @GetMapping("/show_by_name_manufacture")
    public String showByNameManufacture(@RequestParam("name") String name, Model model) {
        List<Souvenir> souvenirs = souvenirRepository.findByManufacture_NameContains(name);
        model.addAttribute("souvenirs", souvenirs);

        return "show_souvenirs_by_name_manufacture";
    }


    @GetMapping("/show_all_souvenirs_by_date")
    public String showAllByDate(Model model) {
        List<Souvenir> souvenirs = souvenirRepository.sortByDate();
        model.addAttribute("souvenirs", souvenirs);
        return "all_souvenirs_by_date";
    }


    @GetMapping("/show_souvenirs_by_year")
    public String showByYear(@RequestParam("date") int date, Model model) {
        List<Souvenir> souvenirs = souvenirRepository.showByYear(date);
        model.addAttribute("souvenirs", souvenirs);
        return "show_by_year";
    }

}
