package ru.whereToEat.service;

import ru.whereToEat.model.User;
import ru.whereToEat.repository.UserRepository;

import java.util.List;

public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User create(User user) {
        return repository.save(user);
    }

    public void delete(int id) {
        repository.delete(id);
    }

    public User get(int id) {
        return repository.get(id);
    }

    /*public User getByEmail(String email) {
        return repository.getByEmail(email), "email=" + email);
    }*/

    public List<User> getAll() {
        return repository.getAll();
    }

    public void update(User user) {
        repository.save(user);
    }

}
