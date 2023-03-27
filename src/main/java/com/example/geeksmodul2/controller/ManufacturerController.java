package com.example.geeksmodul2.controller;

import com.example.geeksmodul2.model.Manufacture;
import com.example.geeksmodul2.repository.ManufactureRepository;
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
public class ManufacturerController {

    ManufactureRepository manufactureRepository;
    ManufactureService manufactureService;
    SouvenirService souvenirService;

    @GetMapping("/manufacturers")
    public String allManufacurers(Model model) {
        List<Manufacture> manufactures = manufactureService.getManufactures();
        model.addAttribute("manufactures", manufactures);
        return "manufacturers";
    }

    @PostMapping("/add_manufacture")
    public String addManufacture(@RequestParam(name = "name") String name
                                ,@RequestParam(name = "country") String country) {
    Manufacture manufacture = new Manufacture();
    manufacture.setName(name);
    manufacture.setCountry(country);
    manufactureRepository.save(manufacture);
    return "redirect:/manufacturers";
    }

    @GetMapping("/manufacture_souvenirs/{id}")
    public String souvenirsByManufacture(@PathVariable("id") int id, Model model) {
       Optional<Manufacture> manufacture = manufactureRepository.findById(id);
       if (manufacture.isEmpty()) {
           return "redirect:/manufacturers";
       }
       model.addAttribute("manufacture", manufacture.get());
       model.addAttribute("souvenirs", manufacture.get().getSouvenirs());
       return "souvenirs_by_manufacture";
    }

    @GetMapping("/delete_manufacture/{id}")
    public String deleteManufacture(@PathVariable("id") int id) {
        manufactureRepository.deleteById(id);
        return "redirect:/manufacturers";
    }

    @GetMapping("edit_manufacture/{id}")
    public String editManufacture(@PathVariable("id") int id, Model model) {
       Optional<Manufacture> manufacture = manufactureRepository.findById(id);
       if (manufacture.isEmpty()) {
           return "redirect:/manufacturers";
       }
       model.addAttribute("manufacture", manufacture.get());
       return "edit_manufacture";
    }

    @PostMapping("/update_manufacture")
    public String updateManufacture(@RequestParam("id") int id,
                                    @RequestParam("name") String name,
                                    @RequestParam("country") String country) {
       Optional<Manufacture> manufacture = manufactureRepository.findById(id);
        if (manufacture.isEmpty()) {
            return "redirect:/manufacturers";
        }
        Manufacture m = manufacture.get();
        m.setName(name);
        m.setCountry(country);
        manufactureRepository.save(m);
        return "redirect:/manufacturers";
    }

    @GetMapping("/sort_by_price_less")
    public String sortByPrice(@RequestParam("price") double price, Model model) {
        List<Manufacture> manufactures = manufactureRepository.findBySouvenirs_PriceLessThan(price);

        model.addAttribute("manufacture", manufactures);

        return "show_manufacture_by_price";
    }


    @GetMapping("/show_manufacturers_by_name_and_year_souvenir")
    public String getManufactureByNameAndDateOfSouvenir(@RequestParam("name") String name,
                                                        @RequestParam("date") LocalDate date,
                                                        Model model) {
        List<Manufacture> manufactures = manufactureRepository.findBySouvenirs_NameContainsAndSouvenirs_Date(name, date);
        model.addAttribute("manufactures", manufactures);
        return "manufactures_by_name_date_of_souvenir";
    }

}
