package ru.job4j.car_accident.store;

import org.springframework.stereotype.Repository;
import ru.job4j.car_accident.model.Accident;
import ru.job4j.car_accident.model.AccidentType;
import ru.job4j.car_accident.model.Rule;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private Map<Integer, Accident> accidents = new HashMap<>();
    private AtomicInteger ACCIDENT_ID = new AtomicInteger(1);
    private Map<Integer,AccidentType> types = new HashMap<>();
    private Map<Integer, Rule> rules = new HashMap<>();

    public AccidentMem() {
        types.put(1, AccidentType.of(1, "Две машины"));
        types.put(2, AccidentType.of(2, "Машина и человек"));
        types.put(3,AccidentType.of(3, "Машина и велосипед"));
        rules.put(1, Rule.of(1, "Статья. 1"));
        rules.put(2, Rule.of(2, "Статья. 2"));
        rules.put(3, Rule.of(3, "Статья. 3"));
    }

    public void create(Accident accident, String[] rIds) {
        accident.setId(ACCIDENT_ID.incrementAndGet());
        Set<Rule> rules = createRules(rIds);
        accident.setRules(rules);
        accidents.put(accident.getId(), accident);
    }

    public void replace(Accident accident) {
        accidents.put(accident.getId(), accident);
    }

    public Collection<Accident> getAll() {
        return accidents.values();
    }

    public Collection<AccidentType> getTypes() {
        return types.values();
    }

    public Collection<Rule> getRules() {
        return rules.values();
    }

    public Accident findById(int id) {
        return accidents.get(id);
    }

    private Set<Rule> createRules(String[] rIds) {
        Set<Rule> rulesList = new HashSet<>();
        for (String id : rIds) {
            String ruleName = rules.get(Integer.parseInt(id)).getName();
            rulesList.add(Rule.of(Integer.parseInt(id), ruleName));
        }
        return rulesList;
    }
}
