package ru.job4j.car_accident.store;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.car_accident.model.AccidentType;

import java.util.List;

@Repository
public class AccidentTypeJdbcTemplate {

    private final JdbcTemplate jdbc;

    public AccidentTypeJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public AccidentType findAccidentTypeId(int id) {
        return jdbc.queryForObject("SELECT id, name FROM accidents_type WHERE id = ?",
                (resultSet, rowNum) -> {
                    AccidentType type = AccidentType.of(
                            resultSet.getInt("id"),
                            resultSet.getString("name"));
                    return type;
                },
                id);
    }

    public List<AccidentType> getAllAccidentType() {
        return jdbc.query("SELECT id, name FROM accidents_type",
                (rs, rowNum) -> AccidentType.of(
                        rs.getInt("id"),
                        rs.getString("name")));
    }
}
