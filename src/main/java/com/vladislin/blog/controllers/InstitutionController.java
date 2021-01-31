package com.vladislin.blog.controllers;

import com.vladislin.blog.models.Institution;
import com.vladislin.blog.repo.InstitutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class InstitutionController {

    @Autowired
    private InstitutionRepository institutionRepository;

    @GetMapping("/institutions")
    public String institution(Model model) {
        Iterable<Institution> institutions = institutionRepository.findAll();
        model.addAttribute("institutions", institutions);
        return "institutions";
    }

    @GetMapping("/institution/add")
    public String institutionAdd() {
        return "institution-add";
    }

    @PostMapping("/institution/add")
    public String institutioninstitutionAdd(@RequestParam String title, @RequestParam String address,
                                  @RequestParam String site, @RequestParam String phone,
                                  @RequestParam float lat, @RequestParam float lon) {
        Institution institution = new Institution(title, address, site, phone, lat, lon);
        institutionRepository.save(institution);
        return "redirect:/institutions";
    }

    @GetMapping("/institution/{id}")
    public String institutionDetails(@PathVariable(value = "id") long id, Model model) {
        if (!institutionRepository.existsById(id)) {
            return "redirect:/institutions";
        }

        Optional<Institution> institution = institutionRepository.findById(id);
        ArrayList<Institution> res = new ArrayList<>();
        institution.ifPresent(res::add);
        model.addAttribute("institution", res);
        model.addAttribute("test", institutionRepository.findById(id).get());
        return "institution-details";
    }

}
