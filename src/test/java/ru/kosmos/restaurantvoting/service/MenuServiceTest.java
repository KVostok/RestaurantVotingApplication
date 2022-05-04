package ru.kosmos.restaurantvoting.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.kosmos.restaurantvoting.dto.DishesDTO;
import ru.kosmos.restaurantvoting.dto.MenuDTO;
import ru.kosmos.restaurantvoting.error.NotFoundException;
import ru.kosmos.restaurantvoting.model.Menu;
import ru.kosmos.restaurantvoting.testdata.MenuTestData;
import ru.kosmos.restaurantvoting.util.MenuUtil;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.kosmos.restaurantvoting.testdata.MenuTestData.*;
import static ru.kosmos.restaurantvoting.testdata.RestaurantTestData.REST_ID;
import static ru.kosmos.restaurantvoting.testdata.UsersTestData.NOT_FOUND;

class MenuServiceTest extends AbstractServiceTest {

    @Autowired
    protected MenuService service;

    @Test
    void get() {
        Menu actualMenu = service.get(MENU_ID);
        actualMenu.setDishes(null);
        actualMenu.setVotes(null);
        MATCHER_EASY.assertMatch(actualMenu, menu);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND));
    }

    @Test
    void getWithDishes() {
        WITH_DISHES_MATCHER.assertMatch(service.getWithDishes(MENU_ID), MenuTestData.menu);
    }

    @Test
    void getWithDishesNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND));
    }

    @Test
    void create() {
        MenuDTO newDto = new MenuDTO(REST_ID + 5, List.of(new DishesDTO(10000, 10),
                new DishesDTO(10001, 20)));
        Menu newMenu = MenuUtil.asEntity(newDto);
        Menu created = service.create(newMenu);
        WITH_DISHES_MATCHER.assertMatch(created, newMenu);
    }

    @Test
    void createDuplicate() {
        MenuDTO newDto = new MenuDTO(REST_ID, List.of(new DishesDTO(10000, 10),
                new DishesDTO(10001, 20)));
        Menu newMenu = MenuUtil.asEntity(newDto);
        assertThrows(DataAccessException.class, () -> service.create(newMenu));
    }

}