package ru.kosmos.restaurantvoting.web.controllers.restaurants;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ru.kosmos.restaurantvoting.dto.RestaurantDTO;
import ru.kosmos.restaurantvoting.model.Restaurant;
import ru.kosmos.restaurantvoting.service.RestaurantService;
import ru.kosmos.restaurantvoting.util.RestaurantUtil;

import java.util.List;

import static ru.kosmos.restaurantvoting.util.validation.ValidationUtil.assureIdConsistent;
import static ru.kosmos.restaurantvoting.util.validation.ValidationUtil.checkNew;

@Slf4j
public abstract class AbstractRestaurantRestController {

    @Autowired
    private RestaurantService restaurantService;

    public Restaurant get(int id) {
        log.info("get restaurant {}", id);
        return restaurantService.get(id);
    }

    public void delete(int id) {
        log.info("delete restaurant {}", id);
        restaurantService.delete(id);
    }

    public Restaurant create(Restaurant restaurant) {
        log.info("create restaurant {}", restaurant);
        checkNew(restaurant);
        return restaurantService.create(restaurant);
    }

    public void update(Restaurant restaurant, int id) {
        log.info("update restaurant {} with id {}", restaurant, id);
        assureIdConsistent(restaurant, id);
        restaurantService.update(restaurant);
    }

    public List<RestaurantDTO> getAllWithMenu() {
        log.info("getAllRestaurantsWithMenu");
        return RestaurantUtil.getDTOs(restaurantService.getAllWithMenu());
    }

}