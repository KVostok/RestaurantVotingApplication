package ru.kosmos.restaurantvoting.repository;

import org.springframework.stereotype.Repository;
import ru.kosmos.restaurantvoting.model.Dish;

import java.util.List;

@Repository
public class DishRepository {

    private final CrudDishRepository repository;

    public DishRepository(CrudDishRepository repository) {
        this.repository = repository;
    }

    public Dish save(Dish dish) {
        return repository.save(dish);
    }

    public boolean delete(int id) {
        return repository.delete(id) != 0;
    }

    public Dish get(int id) {
        return repository.findById(id).orElse(null);
    }

    public List<Dish> getAll() {
        return repository.findAll();
    }

}
