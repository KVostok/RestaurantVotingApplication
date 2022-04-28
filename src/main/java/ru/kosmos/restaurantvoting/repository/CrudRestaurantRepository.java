package ru.kosmos.restaurantvoting.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.kosmos.restaurantvoting.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Restaurant r WHERE r.id=:id")
    int delete(@Param("id") int id);

    //    https://coderoad.ru/9710252/%D0%9A%D0%B0%D0%BA-%D1%81%D0%B4%D0%B5%D0%BB%D0%B0%D1%82%D1%8C-%D1%81%D0%BB%D0%BE%D0%B6%D0%BD%D0%BE%D0%B5-%D1%83%D1%81%D0%BB%D0%BE%D0%B2%D0%B8%D0%B5-LEFT-JOIN-%D0%B2-JPQL
    @EntityGraph(value = "allJoined", type = EntityGraph.EntityGraphType.LOAD)
    @Query("select r from Restaurant r left join r.menues m on m.date = ?1 order by r.name")
    List<Restaurant> findAllRestaurantWithMenuWithDishesWithVotesByDateIsNow(LocalDate today);

}
