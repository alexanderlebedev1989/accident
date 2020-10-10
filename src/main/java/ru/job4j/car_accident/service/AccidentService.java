package ru.job4j.car_accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.car_accident.model.Accident;
import ru.job4j.car_accident.model.AccidentType;
import ru.job4j.car_accident.model.Rule;
import ru.job4j.car_accident.store.AccidentRepository;
import ru.job4j.car_accident.store.AccidentsTypeRepository;
import ru.job4j.car_accident.store.RulesRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccidentService {
    private final AccidentRepository accidents;
    private final AccidentsTypeRepository types;
    private final RulesRepository rules;

    public AccidentService(AccidentRepository accidents,
                           AccidentsTypeRepository types,
                           RulesRepository rules) {
        this.accidents = accidents;
        this.types = types;
        this.rules = rules;
    }

    public void saveOrUpdate(Accident accident, String[] rIds) {
        List<Rule> rulesList = createRules(rIds);
        accident.setRules(rulesList);
        accidents.save(accident);
    }

    public void delete(Accident accident) {
        accidents.delete(accident);
    }

    public List<Accident> getAllAccidents() {
        List<Accident> list = new ArrayList<>();
        accidents.findAll().forEach(list::add);
        return list;
    }

    public Accident findByIdAccident(int id) {
        return accidents.findById(id).get();
    }

    public List<AccidentType> getAllTypes() {
        List<AccidentType> list = new ArrayList<>();
        types.findAll().forEach(list::add);
        return list;
    }

    public List<Rule> getAllRules() {
        List<Rule> list = new ArrayList<>();
        rules.findAll().forEach(list::add);
        return list;
    }

    private Rule findByRuleId(int id) {
        return rules.findById(id).get();
    }

    private List<Rule> createRules(String[] rIds) {
        List<Rule> list = new ArrayList<>();
        for (String id : rIds) {
            String ruleName = findByRuleId(Integer.parseInt(id)).getName();
            list.add(Rule.of(Integer.parseInt(id), ruleName));
        }
        return list;
    }
}
