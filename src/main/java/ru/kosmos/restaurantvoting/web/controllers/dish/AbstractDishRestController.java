package ru.kosmos.restaurantvoting.web.controllers.dish;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ru.kosmos.restaurantvoting.model.Dish;
import ru.kosmos.restaurantvoting.service.DishService;

import java.util.List;

import static ru.kosmos.restaurantvoting.util.validation.ValidationUtil.assureIdConsistent;
import static ru.kosmos.restaurantvoting.util.validation.ValidationUtil.checkNew;

@Slf4j
public abstract class AbstractDishRestController {

    @Autowired
    private DishService dishService;

    public Dish get(int id) {
        log.info("get dish {}", id);
        return dishService.get(id);
    }

    public void delete(int id) {
        log.info("delete dish {}", id);
        dishService.delete(id);
    }

    public Dish create(Dish dish) {
        log.info("create dish {}", dish);
        checkNew(dish);
        return dishService.create(dish);
    }

    public void update(Dish dish, int id) {
        log.info("update dish {} with id {}", dish, id);
        assureIdConsistent(dish, id);
        dishService.update(dish);
    }

    public List<Dish> getAll() {
        log.info("getAll dish");
        return dishService.getAll();
    }

}