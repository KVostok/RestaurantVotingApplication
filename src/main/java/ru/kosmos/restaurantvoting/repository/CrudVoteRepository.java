package ru.kosmos.restaurantvoting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.kosmos.restaurantvoting.model.Votes;

import java.time.LocalDate;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudVoteRepository extends JpaRepository<Votes, Integer> {

    @Query("select v from Votes v join v.menu m where v.user.id=:user_id and m.date=:today")
    Optional<Votes> getWithMenuForUserOnToday(@Param("user_id") int userId, @Param("today") LocalDate today);

    @Query("select v from Votes v join fetch v.user join fetch v.menu where v.id=:vote_id")
    Optional<Votes> getByIdWithMenuWithUser(@Param("vote_id") int voteId);

}
