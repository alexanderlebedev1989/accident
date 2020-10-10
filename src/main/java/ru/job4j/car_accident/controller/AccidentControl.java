package ru.job4j.car_accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.car_accident.model.Accident;
import ru.job4j.car_accident.service.AccidentService;
import javax.servlet.http.HttpServletRequest;

@Controller
public class AccidentControl {

    private final AccidentService service;

    public AccidentControl(AccidentService service) {
        this.service = service;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", service.getAllTypes());
        model.addAttribute("rules", service.getAllRules());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        service.saveOrUpdate(accident, req.getParameterValues("rIds"));
        return "redirect:/";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("types", service.getAllTypes());
        model.addAttribute("rules", service.getAllRules());
        model.addAttribute("accident", service.findByIdAccident(id));
        return "accident/update";
    }

    @GetMapping("/delete")
    public String delete(Model model) {
        model.addAttribute("accidents", service.getAllAccidents());
        return "accident/delete";
    }

    @PostMapping("/delete")
    public String delete(HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("id"));
        Accident accident = service.findByIdAccident(id);
        service.delete(accident);
        return "redirect:/";
    }
}
