package ru.kosmos.restaurantvoting.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.kosmos.restaurantvoting.error.NotFoundException;
import ru.kosmos.restaurantvoting.model.Menu;
import ru.kosmos.restaurantvoting.model.Restaurant;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.kosmos.restaurantvoting.testdata.MenuTestData.*;
import static ru.kosmos.restaurantvoting.testdata.RestaurantTestData.MATCHER;
import static ru.kosmos.restaurantvoting.testdata.RestaurantTestData.*;
import static ru.kosmos.restaurantvoting.testdata.UsersTestData.NOT_FOUND;

class RestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    protected RestaurantService service;

    @Test
    void getAllWithMenu() {
        List<Restaurant> allWithMenu = service.getAllWithMenu();
        assertThat(allWithMenu).isNotNull();
        assertThat(allWithMenu).hasSize(6);
        assertThat(allWithMenu).contains(rest1);
        assertThat(allWithMenu.get(allWithMenu.indexOf(rest1)).getMenues()).contains(menu);
        List<Menu> menues = List.copyOf(allWithMenu.get(allWithMenu.indexOf(rest1)).getMenues());
        Menu menuRest1 = menues.get(menues.indexOf(menu));
        assertThat(menuRest1.getDishes()).containsAll(List.of(dishes1, dishes2, dishes3, dishes4,
                dishes5, dishes6));
        assertThat(menuRest1.getVotes()).containsAll(List.of(vote1, vote2, vote3, vote4, vote5));
    }

    @Test
    void get() {
        Restaurant restaurant = service.get(REST_ID);
        MATCHER.assertMatch(restaurant, rest1);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND));
    }

    @Test
    void delete() {
        service.delete(REST_ID);
        assertThrows(NotFoundException.class, () -> service.get(REST_ID));
    }

    @Test
    void deletedNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND));
    }

    @Test
    void create() {
        Restaurant created = service.create(getNewRestaurant());
        int newId = created.id();
        Restaurant newRestaurant = getNewRestaurant();
        newRestaurant.setId(newId);
        MATCHER.assertMatch(created, newRestaurant);
        MATCHER.assertMatch(service.get(newId), newRestaurant);
    }

    @Test
    void duplicateNameCreate() {
        assertThrows(DataAccessException.class, () -> service.create(new Restaurant(null, "Радуга")));
    }

    @Test
    void createWithException() {
        assertThrows(ConstraintViolationException.class, () -> service.create(new Restaurant(null, "")));
    }

    @Test
    void update() {
        Restaurant updated = getUpdatedRestaurant();
        service.update(updated);
        MATCHER.assertMatch(service.get(REST_ID), getUpdatedRestaurant());
    }

}