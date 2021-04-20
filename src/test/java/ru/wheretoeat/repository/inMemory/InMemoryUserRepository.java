package ru.wheretoeat.repository.inMemory;

import org.springframework.stereotype.Repository;
import ru.wheretoeat.UserTestData;
import ru.wheretoeat.model.User;
import ru.wheretoeat.repository.UserRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryUserRepository implements UserRepository {


    static Map<Integer, User> map = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(99999);

    public void init() {
        map.clear();
        map.put(UserTestData.USER_ID, UserTestData.USER);
        map.put(UserTestData.ADMIN_ID, UserTestData.ADMIN);
    }

    @Override
    public User save(User user) {
        if (user.isNew()) {
            user.setId(counter.incrementAndGet());
            map.put(user.getId(), user);
            return user;
        }
        return map.computeIfPresent(user.getId(), (id, oldUser) -> user);
    }

    @Override
    public boolean delete(Integer id) {
        return map.remove(id) != null;
    }

    @Override
    public User get(Integer id) {
        return map.get(id);
    }

    @Override
    public List<User> getAll() {
        return map.values()
                .stream()
                .sorted(Comparator.comparing(User::getName).thenComparing(User::getEmail))
                .collect(Collectors.toList());
    }

    @Override
    public User getByEmail(String email) {
        return map.values().stream()
                .filter((user -> user.getEmail().equals(email)))
                .findFirst().orElse(null);
    }
}
