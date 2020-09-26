package ru.whereToEat.repository.inMemory;

import org.springframework.stereotype.Repository;
import ru.whereToEat.model.User;
import ru.whereToEat.repository.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InMemoryUserRepository implements UserRepository {
    static Map<Integer, User> storage = new HashMap<>();

    @Override
    public User save(User user) {
        return storage.put(user.getId(), user);
    }

    @Override
    public boolean delete(Integer id) {
        return storage.remove(id) != null;
    }

    @Override
    public User get(Integer id) {
        return storage.get(id);
    }

    @Override
    public List<User> getAll() {
        return (List<User>) storage.values();
    }

    @Override
    public User getByEmail(String email) {
        return null;
    }
}
