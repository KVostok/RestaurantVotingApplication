package ru.kosmos.restaurantvoting.repository;

import org.springframework.stereotype.Repository;
import ru.kosmos.restaurantvoting.model.Menu;

@Repository
public class MenuRepository {

    private final CrudMenuRepository repository;

    public MenuRepository(CrudMenuRepository repository) {
        this.repository = repository;
    }

    public Menu save(Menu menu) {
        return repository.save(menu);
    }

    public Menu getWithDishes(int id) {
        return repository.getWithDishes(id);
    }

    public Menu get(int id) {
        return repository.findById(id).orElse(null);
    }

}
