package ru.whereToEat.repository;

import ru.whereToEat.model.User;

import java.util.List;

public interface UserRepository {
    // null if not found, when updated
    User save(User user);

    // false if not found
    boolean delete(Integer id);

    // null if not found
    User get(Integer id);

    // emptylist if not found
    List<User> getAll();

}
