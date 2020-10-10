package ru.job4j.car_accident.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.car_accident.model.Accident;
import ru.job4j.car_accident.model.AccidentType;
import ru.job4j.car_accident.model.Rule;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AccidentHibernate {
    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    public void saveOrUpdate(Accident accident, String[] rIds) {
        accident.setRules(createRules(rIds));
        if (accident.getId() == 0) {
            save(accident);
        } else {
            update(accident);
        }
    }

    public void save(Accident accident) {
        try (Session session = sf.openSession()) {
            session.getTransaction().begin();
            session.save(accident);
            session.getTransaction().commit();

        }
    }

    public void update(Accident accident) {
        try(Session session = sf.openSession()) {
            session.getTransaction().begin();
            session.update(accident);
            session.getTransaction().commit();
        }
    }

    public Accident findById(int id) {
        try (Session session = sf.openSession()) {
            return session.get(Accident.class, id);
        }
    }

    public List<Accident> getAllAccidents() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("FROM Accident", Accident.class)
                    .list();
        }
    }

    public List<AccidentType> getAllTypes() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("FROM AccidentType", AccidentType.class)
                    .list();
        }
    }

    public List<Rule> getAllRules() {
        try(Session session = sf.openSession()) {
            return session
                    .createQuery("FROM Rule", Rule.class)
                    .list();
        }
    }

    private Rule findByRuleId(int id) {
        try(Session session = sf.openSession()) {
            return session.get(Rule.class, id);
        }
    }

    private List<Rule> createRules(String[] rIds) {
        List<Rule> rulesList = new ArrayList<>();
        for (String id : rIds) {
            String ruleName = findByRuleId(Integer.parseInt(id)).getName();
            rulesList.add(Rule.of(Integer.parseInt(id), ruleName));
        }
        return rulesList;
    }

    public void delete(Accident accident) {
        try(Session session = sf.openSession()) {
            session.getTransaction().begin();
            session.remove(accident);
            session.getTransaction().commit();
        }
    }
}
