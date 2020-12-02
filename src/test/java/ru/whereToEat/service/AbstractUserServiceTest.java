package ru.whereToEat.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.whereToEat.UserTestData;
import ru.whereToEat.exceptions.NotFoundException;
import ru.whereToEat.model.Role;
import ru.whereToEat.model.User;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static ru.whereToEat.UserTestData.*;

abstract public class AbstractUserServiceTest extends AbstractServiceTest {

    @Autowired
    protected UserService service;

    @Test
    public void create() {
        User created = service.create(getNew());
        User newUser = UserTestData.getNew();
        Integer newId = created.getId();
        newUser.setId(newId);
        User getUser = service.get(newId);
        assertMatch(created, newUser);
        assertMatch(getUser, newUser);
    }

    @Test
    public void duplicateMailCreate() {
        assertThrows(DataAccessException.class, () ->
                service.create(new User(null, "name", "user@yandex.ru", "Duplicate", true, LocalDateTime.now(), Role.USER)));
    }

    @Test
    public void delete() {
        service.delete(USER_ID);
        assertThrows(NotFoundException.class, () -> service.delete(USER_ID));

    }

    @Test
    public void deletedNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND));
    }

    @Test
    public void get() {
        User user = service.get(USER_ID);
        assertMatch(user, USER);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND));
    }

    @Test
    public void getByEmail() {
        User user = service.getByEmail("admin@gmail.com");
        assertMatch(user, ADMIN);
    }

    @Test
    public void update() {
        service.update(getUpdated());
        User updated = getUpdated();
        assertMatch(service.get(USER_ID), updated);
    }

    @Test
    public void getAll() {
        List<User> all = service.getAll();
        assertMatch(all, ADMIN, USER);
    }


}
