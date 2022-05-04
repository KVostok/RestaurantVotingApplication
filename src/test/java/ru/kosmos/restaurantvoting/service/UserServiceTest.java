package ru.kosmos.restaurantvoting.service;

import org.hibernate.PersistentObjectException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.kosmos.restaurantvoting.error.NotFoundException;
import ru.kosmos.restaurantvoting.model.Users;

import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static ru.kosmos.restaurantvoting.testdata.UsersTestData.*;

class UserServiceTest extends AbstractServiceTest {

    @Autowired
    protected UserService service;

    @Test
    void create() {
        Users created = service.create(getNewUser());
        int newId = created.id();
        Users newUser = getNewUser();
        newUser.setId(newId);
        MATCHER.assertMatch(created, newUser);
        MATCHER.assertMatch(service.get(newId), newUser);
    }

    @Test
    void duplicateMailCreate() {
        assertThrows(DataAccessException.class, () ->
                service.create(new Users(null, "Duplicate", "user1@yandex.ru", "newPass", true, new Date(), Set.of(userRoles))));
    }

    @Test
    void createWithException() {
        validateRootCause(ConstraintViolationException.class, () -> service.create(new Users(null, "  ", "mail@yandex.ru", "password", true, new Date(), Set.of(userRoles))));
        validateRootCause(ConstraintViolationException.class, () -> service.create(new Users(null, "User", "  ", "password", true, new Date(), Set.of(userRoles))));
        validateRootCause(ConstraintViolationException.class, () -> service.create(new Users(null, "User", "mail@yandex.ru", "password", true, null, Set.of(userRoles))));
        validateRootCause(PersistentObjectException.class, () -> service.create(new Users(null, "User", "mail@yandex.ru", "pass", true, new Date(), Set.of(userRoles))));
    }

    @Test
    void delete() {
        service.switchOfModificationRestriction();
        service.delete(USER_ID);
        service.switchOnModificationRestriction();
        assertThrows(NotFoundException.class, () -> service.get(USER_ID));
    }

    @Test
    void deletedNotFound() {
        service.switchOfModificationRestriction();
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND));
        service.switchOnModificationRestriction();
    }

    @Test
    void get() {
        Users user = service.get(ADMIN_ID);
        MATCHER.assertMatch(user, admin);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND));
    }

    @Test
    void getByIdWithRoles() {
        WITH_ROLES_MATCHER.assertMatch(service.getByIdWithRoles(ADMIN_ID), admin);
    }

    @Test
    void getByIdWithRolesNotFound() {
        assertThrows(NotFoundException.class, () -> service.getByIdWithRoles(NOT_FOUND));
    }

    @Test
    void getByEmail() {
        Users user = service.getByEmail("admin@gmail.com");
        MATCHER.assertMatch(user, admin);
    }

    @Test
    void getByEmailNotFound() {
        assertThrows(NotFoundException.class, () -> service.getByEmail("you@me.ru"));
    }

    @Test
    void getAll() {
        List<Users> all = service.getAll();
        MATCHER.assertMatch(all, admin, user1, user2, user3, user4, user5, user6, user7, user8, user9,
                user10, user11, user12, user13, user14, user15, user16, user17, user18, user19, user20);
    }

    @Test
    void enable() {
        service.switchOfModificationRestriction();
        service.enable(USER_ID, false);
        assertFalse(service.get(USER_ID).isEnabled());
        service.enable(USER_ID, true);
        assertTrue(service.get(USER_ID).isEnabled());
        service.switchOnModificationRestriction();
    }

    @Test
    void update() {
        service.switchOfModificationRestriction();
        Users updated = getUpdatedUser();
        service.update(updated);
        service.switchOnModificationRestriction();
        MATCHER.assertMatch(service.get(USER_ID), getUpdatedUser());
    }

}