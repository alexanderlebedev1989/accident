package ru.job4j.car_accident.controller;

import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.car_accident.service.AccidentService;

@Controller
public class IndexControl {
    private final AccidentService service;

    public IndexControl(AccidentService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("accidents", service.getAllAccidents());
        return "index";
    }
}
