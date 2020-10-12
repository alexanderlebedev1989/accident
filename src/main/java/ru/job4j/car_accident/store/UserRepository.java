package ru.job4j.car_accident.store;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.car_accident.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
}