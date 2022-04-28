package ru.kosmos.restaurantvoting.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.kosmos.restaurantvoting.model.Dish;
import ru.kosmos.restaurantvoting.repository.DishRepository;

import java.util.List;

import static ru.kosmos.restaurantvoting.util.validation.ValidationUtil.checkNotFoundWithId;

@Service
public class DishService {

    private final DishRepository dishRepository;

    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public List<Dish> getAll() {
        return dishRepository.getAll();
    }

    public Dish get(int id) {
        return checkNotFoundWithId(dishRepository.get(id), id);
    }

    public void delete(int id) {
        checkNotFoundWithId(dishRepository.delete(id), id);
    }

    public Dish create(Dish dish) {
        Assert.notNull(dish, "dish must not be null");
        return dishRepository.save(dish);
    }

    public Dish update(Dish dish) {
        Assert.notNull(dish, "dish must not be null");
        return checkNotFoundWithId(dishRepository.save(dish), dish.id());
    }

}
