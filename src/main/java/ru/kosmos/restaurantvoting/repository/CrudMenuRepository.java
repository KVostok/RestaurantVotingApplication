package ru.kosmos.restaurantvoting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.kosmos.restaurantvoting.model.Menu;

@Transactional(readOnly = true)
public interface CrudMenuRepository extends JpaRepository<Menu, Integer> {

    @Query("from Menu m left join fetch m.dishes md left join fetch md.dish where m.id=:id")
    Menu getWithDishes(@Param("id") int id);

}
