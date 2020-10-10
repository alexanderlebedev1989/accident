package ru.job4j.car_accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.car_accident.model.Accident;
import ru.job4j.car_accident.model.AccidentType;
import ru.job4j.car_accident.model.Rule;
import ru.job4j.car_accident.store.AccidentHibernate;

import java.util.List;

@Service
public class AccidentService {
    private final AccidentHibernate database;

    public AccidentService(AccidentHibernate database) {
      this.database = database;
    }

    public void saveOrUpdate(Accident accident, String[] rIds) {
       database.saveOrUpdate(accident, rIds);
    }

    public void delete(Accident accident) {
        database.delete(accident);
    }

    public List<Accident> getAllAccidents() {
        return database.getAllAccidents();
    }

    public Accident findByIdAccident(int id) {
        return database.findById(id);
    }

    public List<AccidentType> getAllTypes() {
        return database.getAllTypes();
    }

    public List<Rule> getAllRules() {
        return database.getAllRules();
    }
}
