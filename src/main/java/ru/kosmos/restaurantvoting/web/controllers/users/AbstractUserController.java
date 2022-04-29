package ru.kosmos.restaurantvoting.web.controllers.users;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ru.kosmos.restaurantvoting.dto.UsersDTO;
import ru.kosmos.restaurantvoting.model.Users;
import ru.kosmos.restaurantvoting.service.UserService;

import java.util.List;

import static ru.kosmos.restaurantvoting.util.validation.ValidationUtil.assureIdConsistent;
import static ru.kosmos.restaurantvoting.util.validation.ValidationUtil.checkNew;

@Slf4j
public class AbstractUserController {

    @Autowired
    private UserService service;

    public List<Users> getAll() {
        log.info("getAll users");
        return service.getAll();
    }

    public Users get(int id) {
        log.info("get {}", id);
        return service.get(id);
    }

    public Users getByIdWithRoles(int id) {
        log.info("getByIdWithRoles {}", id);
        return service.getByIdWithRoles(id);
    }

    public Users create(Users users) {
        log.info("create {}", users);
        checkNew(users);
        return service.create(users);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    public void update(Users users, int id) {
        log.info("update {} with id={}", users, id);
        assureIdConsistent(users, id);
        service.update(users);
    }

    public void update(UsersDTO usersDto, int id) {
        log.info("update {} with id={}", usersDto, id);
        assureIdConsistent(usersDto, id);
        service.update(usersDto);
    }

    public Users getByMail(String email) {
        log.info("getByEmail {}", email);
        return service.getByEmail(email);
    }

    public void enable(int id, boolean enabled) {
        log.info(enabled ? "enable {}" : "disable {}", id);
        service.enable(id, enabled);
    }

}