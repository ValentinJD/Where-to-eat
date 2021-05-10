package ru.wheretoeat.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import ru.wheretoeat.Profiles;
import ru.wheretoeat.exceptions.ModificationRestrictionException;
import ru.wheretoeat.exceptions.NotFoundException;
import ru.wheretoeat.model.AbstractBaseEntity;
import ru.wheretoeat.model.Role;
import ru.wheretoeat.model.User;
import ru.wheretoeat.service.UserService;
import ru.wheretoeat.to.UserTo;
import ru.wheretoeat.util.UserUtil;

import java.util.List;

import static ru.wheretoeat.util.ValidationUtil.assureIdConsistent;
import static ru.wheretoeat.util.ValidationUtil.checkNew;

public abstract class AbstractUserController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private boolean modificationRestriction;

    @Autowired
    @SuppressWarnings("deprecation")
    public void setEnvironment(Environment environment) {
        modificationRestriction = environment.acceptsProfiles(Profiles.HEROKU);
    }


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

    public User createAdmin(UserTo userTo) {
        log.info("create from to {}", userTo);
        User admin = UserUtil.createNewFromTo(userTo);
        admin.setRole(Role.ADMIN);
        return create(admin);
    }

    public void update(UserTo userTo, int id) {
        log.info("update {} with id={}", userTo, id);
        assureIdConsistent(userTo, id);
        checkModificationAllowed(id);
        service.update(userTo);
    }


    public void delete(int id) {
        log.info("delete {}", id);
        checkModificationAllowed(id);
        service.delete(id);
    }

    public void update(User user, int id) {
        log.info("update {} with id={}", user, id);
        checkModificationAllowed(id);
        service.update(user);
    }

    public User getByMail(String email) throws NotFoundException {
        log.info("getByEmail {}", email);
        return service.getByEmail(email);
    }

    public void enable(int id, boolean enabled) {
        log.info(enabled ? "enable {}" : "disable {}", id);
        checkModificationAllowed(id);
        service.enable(id, enabled);
    }


    private void checkModificationAllowed(int id) {
        if (modificationRestriction && id < AbstractBaseEntity.START_SEQ + 2) {
            throw new ModificationRestrictionException();
        }
    }
}