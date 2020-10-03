package ru.job4j.car_accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.car_accident.model.Accident;
import ru.job4j.car_accident.model.AccidentType;
import ru.job4j.car_accident.model.Rule;
import ru.job4j.car_accident.store.AccidentMem;
import java.util.Collection;

@Service
public class AccidentService {
    private final AccidentMem accidentMem;

    public AccidentService(AccidentMem accidentMem) {
        this.accidentMem = accidentMem;
    }

    public void save(Accident accident, String[] rIds) {
        accidentMem.create(accident, rIds);
    }

    public void update(Accident accident) {
        accidentMem.replace(accident);
    }

    public Collection<Accident> getAllAccidents() {
        return accidentMem.getAll();
    }

    public Accident findByIdAccident(int id) {
        return accidentMem.findById(id);
    }

    public Collection<AccidentType> getAllTypes() {
        return accidentMem.getTypes();
    }

    public Collection<Rule> getAllRules() {
        return accidentMem.getRules();
    }
}
