package ru.kosmos.restaurantvoting.repository;

import org.springframework.stereotype.Repository;
import ru.kosmos.restaurantvoting.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

@Repository
public class RestaurantRepository {

    private final CrudRestaurantRepository repository;

    public RestaurantRepository(CrudRestaurantRepository repository) {
        this.repository = repository;
    }

    public Restaurant save(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    public boolean delete(int id) {
        return repository.delete(id) != 0;
    }

    public Restaurant get(int id) {
        return repository.findById(id).orElse(null);
    }

    public List<Restaurant> getAllWithMenu() {
        return repository.findAllRestaurantWithMenuWithDishesWithVotesByDateIsNow(LocalDate.now());
    }

}
