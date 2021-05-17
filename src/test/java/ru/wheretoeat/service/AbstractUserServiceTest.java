package ru.wheretoeat.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.wheretoeat.UserTestData;
import ru.wheretoeat.exceptions.NotFoundException;
import ru.wheretoeat.model.Role;
import ru.wheretoeat.model.User;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.wheretoeat.UserTestData.*;

abstract public class AbstractUserServiceTest extends AbstractServiceTest {

    @Autowired
    protected UserService service;

    @Test
    void create() {
        User created = service.create(getNew());
        User newUser = UserTestData.getNew();
        Integer newId = created.getId();
        newUser.setId(newId);
        User getUser = service.get(newId);
        USER_MATCHER.assertMatch(created, newUser);
        USER_MATCHER.assertMatch(getUser, newUser);
    }

    @Test
    public void duplicateMailCreate() {
        assertThrows(DataAccessException.class, () ->
                service.create(new User(null, "name", "user@yandex.ru", "Duplicate", true, LocalDateTime.now(), Role.USER)));
    }

    @Test
    void delete() {
        service.delete(USER_ID);
        assertThrows(NotFoundException.class, () -> service.delete(USER_ID));

    }

    @Test
    void deletedNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND));
    }

    @Test
    void get() {
        User user = service.get(USER_ID);
        USER_MATCHER.assertMatch(user, USER);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND));
    }

    @Test
    void getByEmail() {
        User user = service.getByEmail("admin@gmail.com");
        USER_MATCHER.assertMatch(user, ADMIN);
    }

    @Test
    void update() {
        service.update(getUpdated());
        User updated = getUpdated();
        USER_MATCHER.assertMatch(service.get(USER_ID), updated);
    }

    @Test
    void getAll() {
        List<User> all = service.getAll();
        USER_MATCHER.assertMatch(all, ADMIN, USER);
    }

    //    @Ignore
    @Test
    public void createWithException() throws Exception {
        validateRootCause(() -> service.create(new User(null, "  ", "mail@yandex.ru", "password", Role.USER)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User(null, "User", "  ", "password", Role.USER)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User(null, "User", "mail@yandex.ru", "  ", Role.USER)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User(null, "User", "mail@yandex.ru", "password", true, LocalDateTime.now(), null)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User(null, "User", "mail@yandex.ru", "password", true, null, Role.USER)), ConstraintViolationException.class);
    }

    @Test
    void enable() {
        service.enable(USER_ID, false);
        assertFalse(service.get(USER_ID).isEnabled());
        service.enable(USER_ID, true);
        assertTrue(service.get(USER_ID).isEnabled());
    }
}
