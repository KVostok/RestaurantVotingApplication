package ru.kosmos.restaurantvoting.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.kosmos.restaurantvoting.error.NotFoundException;
import ru.kosmos.restaurantvoting.model.Dish;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.kosmos.restaurantvoting.testdata.DishTestData.*;
import static ru.kosmos.restaurantvoting.testdata.UsersTestData.NOT_FOUND;

class DishServiceTest extends AbstractServiceTest {

    @Autowired
    protected DishService service;

    @Test
    void getAll() {
        List<Dish> all = service.getAll();
        MATCHER.assertMatch(all, dishes);
    }

    @Test
    void get() {
        Dish dish = service.get(DISH_ID);
        MATCHER.assertMatch(dish, dish1);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND));
    }

    @Test
    void delete() {
        service.delete(DISH_ID);
        assertThrows(NotFoundException.class, () -> service.get(DISH_ID));
    }

    @Test
    void deletedNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND));
    }

    @Test
    void create() {
        Dish created = service.create(getNewDish());
        int newId = created.id();
        Dish newDish = getNewDish();
        newDish.setId(newId);
        MATCHER.assertMatch(created, newDish);
        MATCHER.assertMatch(service.get(newId), newDish);
    }

    @Test
    void duplicateNameCreate() {
        assertThrows(DataAccessException.class, () -> service.create(new Dish(null, "Салат")));
    }

    @Test
    void createWithException() {
        assertThrows(ConstraintViolationException.class, () -> service.create(new Dish(null, "")));
    }

    @Test
    void update() {
        Dish updated = getUpdatedDish();
        service.update(updated);
        MATCHER.assertMatch(service.get(DISH_ID), getUpdatedDish());
    }

}