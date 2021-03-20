package ru.whereToEat.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.whereToEat.exceptions.NotFoundException;
import ru.whereToEat.model.User;
import ru.whereToEat.service.UserService;
import ru.whereToEat.to.UserTo;
import ru.whereToEat.util.UserUtil;

import java.util.List;

import static ru.whereToEat.util.ValidationUtil.assureIdConsistent;
import static ru.whereToEat.util.ValidationUtil.checkNew;

public abstract class AbstractUserController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService service;

    public List<User> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public User get(int id) {
        log.info("get {}", id);
        return service.get(id);
    }

    public User create(User user) {
        log.info("create {}", user);
        checkNew(user);
        return service.create(user);
    }

    public User create(UserTo userTo) {
        log.info("create from to {}", userTo);
        return create(UserUtil.createNewFromTo(userTo));
    }

    public void update(UserTo userTo, int id) {
        log.info("update {} with id={}", userTo, id);
        assureIdConsistent(userTo, id);
        service.update(userTo);
    }


    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    public void update(User user, int id) {
        log.info("update {} with id={}", user, id);
       // assureIdConsistent(user, id);
        service.update(user);
    }

    public User getByMail(String email) throws NotFoundException {
        log.info("getByEmail {}", email);
        return service.getByEmail(email);
    }

    public void enable(int id, boolean enabled) {
        log.info(enabled ? "enable {}" : "disable {}", id);
        service.enable(id, enabled);
    }
}