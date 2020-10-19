package ru.whereToEat.repository.inMemory;

import org.springframework.stereotype.Repository;
import ru.whereToEat.model.User;
import ru.whereToEat.repository.UserRepository;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryUserRepository implements UserRepository {
    public static final int USER_ID = 1;
    public static final int ADMIN_ID = 2;

    static Map<Integer, User> usersMap = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    @Override
    public User save(User user) {
        if (user.isNew()) {
            user.setId(counter.incrementAndGet());
            usersMap.put(user.getId(), user);
            return user;
        }
        return usersMap.computeIfPresent(user.getId(), (id, oldUser) -> user);
    }

    @Override
    public boolean delete(Integer id) {
        return usersMap.remove(id) != null;
    }

    @Override
    public User get(Integer id) {
        return usersMap.get(id);
    }

    @Override
    public List<User> getAll() {
        return usersMap.values()
                .stream()
                .sorted(Comparator.comparing(User::getName).thenComparing(User::getEmail))
                .collect(Collectors.toList());
    }

    @Override
    public User getByEmail(String email) {
        return usersMap.values().stream()
                .filter((user -> user.getEmail().equals(email)))
                .findFirst().orElse(null);
    }
}
