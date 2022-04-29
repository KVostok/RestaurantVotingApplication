package ru.kosmos.restaurantvoting.testdata;

import ru.kosmos.restaurantvoting.MatcherFactory;
import ru.kosmos.restaurantvoting.dto.RestaurantDTO;
import ru.kosmos.restaurantvoting.model.Restaurant;

import java.util.Set;

import static ru.kosmos.restaurantvoting.model.BaseEntity.START_SEQ;
import static ru.kosmos.restaurantvoting.testdata.MenuTestData.menu;

public class RestaurantTestData {

    public static final MatcherFactory.Matcher<Restaurant> MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class, "menues");
    public static final MatcherFactory.Matcher<RestaurantDTO> DTO_MATCHER = MatcherFactory.usingEqualsComparator(RestaurantDTO.class);

    public static final int REST_ID = START_SEQ;

    public static final Restaurant rest1 = new Restaurant(REST_ID, "Радуга");

    static {
        rest1.setMenues(Set.of(menu));
    }

    public static Restaurant getNewRestaurant() {
        return new Restaurant(null, "NewRestaurant");
    }

    public static Restaurant getUpdatedRestaurant() {
        return new Restaurant(REST_ID, "UpdatedName");
    }

}
