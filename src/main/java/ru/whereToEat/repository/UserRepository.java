package ru.whereToEat.repository;

import ru.whereToEat.model.User;

import java.util.List;

public interface UserRepository {
    // null if not found, when updated
    User save(User user);

    // false if not found
    boolean delete(int id);

    // NotFoundException if not found
    User get(int id);

    List<User> getAll();

}
