package ru.wheretoeat.web.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.wheretoeat.model.User;
import ru.wheretoeat.to.UserTo;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

@ApiIgnore
@RestController
@RequestMapping("/admin/users")
public class AdminUIController extends AbstractUserController {

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User get(@PathVariable int id) {
        return super.get(id);
    }


    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void createOrUpdate(@Valid UserTo userTo) {

        if (userTo.isNew()) {
            super.create(userTo);
        } else {
            super.update(userTo, userTo.id());
        }
    }

    @PostMapping(value = "/admin")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void createAdminOnUI(@Valid UserTo userTo) {

        userTo.setId(null);
        super.createAdmin(userTo);

    }

    @Override
    @PostMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void enable(@PathVariable int id, @RequestParam boolean enabled) {
        super.enable(id, enabled);
    }
}
