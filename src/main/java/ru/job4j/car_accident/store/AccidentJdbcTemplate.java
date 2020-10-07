package ru.job4j.car_accident.store;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.car_accident.model.Accident;
import ru.job4j.car_accident.model.Rule;

import java.sql.PreparedStatement;
import java.util.*;

@Repository
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;
    private AccidentTypeJdbcTemplate typeJdbc;
    private RuleJdbcTemplate ruleJdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc,
                                AccidentTypeJdbcTemplate typeJdbc,
                                RuleJdbcTemplate ruleJdbc) {
        this.jdbc = jdbc;
        this.typeJdbc = typeJdbc;
        this.ruleJdbc = ruleJdbc;
    }

    public void createOrReplace(Accident accident, String[] rIds) {
        Set<Rule> rules = ruleJdbc.createRules(rIds);
        accident.setRules(rules);
        if (accident.getId() == 0) {
            create(accident, rIds);
        } else {
            replace(accident, rIds);
        }
        ruleJdbc.createRuleForAccident(accident);
    }

    public void create(Accident accident, String[] rIds) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sqlCommand = "INSERT INTO accidents (name, text, address, type_id) values (?, ?, ?, ?)";
        jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sqlCommand, new String[]{"id"});
            ps.setString(1, accident.getName());
            ps.setString(2, accident.getText());
            ps.setString(3, accident.getAddress());
            ps.setInt(4, accident.getType().getId());
            return ps;
        }, keyHolder);
        if (keyHolder.getKey() != null) {
            accident.setId(keyHolder.getKey().intValue());
        }
    }

    public void replace(Accident accident, String[] rIds) {
        jdbc.update("UPDATE accidents SET name = ?, text = ?, address = ?, type_id = ? WHERE id = ?",
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getType().getId(),
                accident.getId());
        ruleJdbc.delete(accident.getId());
    }

    public void delete(int id) {
        ruleJdbc.delete(id);
        jdbc.update("DELETE FROM accidents WHERE id = ?", id);

    }

    public Accident findAccidentById(int id) {
        return jdbc.queryForObject(
                "SELECT id, name, text, address, type_id FROM accidents WHERE id = ?",
                (resultSet, rowNum) -> {
                    Accident accident = new Accident(
                            resultSet.getString("name"),
                            resultSet.getString("text"),
                            resultSet.getString("address"));
                    accident.setType(typeJdbc.findAccidentTypeId(resultSet.getInt("type_id")));
                    accident.setId(resultSet.getInt("id"));
                    accident.setRules(ruleJdbc.findAllRulesForAccident(resultSet.getInt("id")));
                    return accident;
                }, id);
    }

    public List<Accident> getAllAccidents() {
        return jdbc.query("SELECT id, name, text, address, type_id FROM accidents",
                (rs, row) -> {
                    Accident accident = new Accident(
                            rs.getString("name"),
                            rs.getString("text"),
                            rs.getString("address"));
                    accident.setType(typeJdbc.findAccidentTypeId(rs.getInt("type_id")));
                    accident.setId(rs.getInt("id"));
                    return accident;
                });
    }
}
