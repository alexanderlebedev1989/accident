package ru.job4j.car_accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.car_accident.model.Accident;
import ru.job4j.car_accident.model.AccidentType;
import ru.job4j.car_accident.model.Rule;
import ru.job4j.car_accident.store.AccidentJdbcTemplate;
import ru.job4j.car_accident.store.AccidentTypeJdbcTemplate;
import ru.job4j.car_accident.store.RuleJdbcTemplate;

import java.util.List;

@Service
public class AccidentService {
    private final AccidentJdbcTemplate jdbcTemplate;
    private final AccidentTypeJdbcTemplate typeJdbc;
    private final RuleJdbcTemplate ruleJdbc;

    public AccidentService(AccidentJdbcTemplate jdbcTemplate,
                           RuleJdbcTemplate ruleJdbc,
                           AccidentTypeJdbcTemplate typeJdbc) {
        this.jdbcTemplate = jdbcTemplate;
        this.ruleJdbc = ruleJdbc;
        this.typeJdbc = typeJdbc;
    }

    public void saveOrUpdate(Accident accident, String[] rIds) {
        jdbcTemplate.createOrReplace(accident, rIds);
    }

    public void delete(int id) {
        jdbcTemplate.delete(id);
    }

    public List<Accident> getAllAccidents() {
        return jdbcTemplate.getAllAccidents();
    }

    public Accident findByIdAccident(int id) {
        return jdbcTemplate.findAccidentById(id);
    }

    public List<AccidentType> getAllTypes() {
        return typeJdbc.getAllAccidentType();
    }

    public List<Rule> getAllRules() {
        return ruleJdbc.getRules();
    }
}
