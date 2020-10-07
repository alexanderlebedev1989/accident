package ru.job4j.car_accident.store;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.car_accident.model.Accident;
import ru.job4j.car_accident.model.Rule;

import java.sql.PreparedStatement;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class RuleJdbcTemplate {

    private final JdbcTemplate jdbc;

    public RuleJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Rule> getRules() {
        return jdbc.query("SELECT id, name FROM rules",
                (rs, rowNum) -> Rule.of(
                        rs.getInt("id"),
                        rs.getString("name")));
    }

    public Set<Rule> createRules(String[] rIds) {
        Set<Rule> rulesList = new HashSet<>();
        for (String id : rIds) {
            String ruleName = findRuleId(Integer.parseInt(id)).getName();
            rulesList.add(Rule.of(Integer.parseInt(id), ruleName));
        }
        return rulesList;
    }

    public Rule findRuleId(int id) {
        return jdbc.queryForObject("SELECT id, name FROM rules WHERE id = ?",
                (resultSet, rowNum) -> Rule.of(
                        resultSet.getInt("id"),
                        resultSet.getString("name")),
                id);
    }

    public void createRuleForAccident(Accident accident) {
        for (Rule rule : accident.getRules()) {
            jdbc.update("INSERT INTO accidents_rules (rules_id, accident_id) VALUES (?, ?)",
                    rule.getId(),
                    accident.getId());
        }
    }

    public Set<Rule> findAllRulesForAccident(int id) {
        List<Rule> list = jdbc.query("SELECT rules_id FROM accidents_rules WHERE accident_id = ?",
                (rs, rowNum) -> {
                    Rule rule = findRuleId(rs.getInt("rules_id"));
                    return rule;
                }, id);
        return new HashSet<>(list);
    }

    public void delete(int id) {
        jdbc.update("DELETE FROM accidents_rules WHERE accident_id = ?", id);
    }
}
