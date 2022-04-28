package ru.kosmos.restaurantvoting.repository;

import org.springframework.stereotype.Repository;
import ru.kosmos.restaurantvoting.model.Votes;

import java.time.LocalDate;

@Repository
public class VoteRepository {

    private final CrudVoteRepository repository;

    public VoteRepository(CrudVoteRepository repository) {
        this.repository = repository;
    }

    public Votes getByIdWithMenuWithUser(int id) {
        return repository.getByIdWithMenuWithUser(id).orElse(null);
    }

    public Votes save(Votes votes) {
        return repository.save(votes);
    }

    public Votes getForUser(int userId) {
        return repository.getWithMenuForUserOnToday(userId, LocalDate.now()).orElse(null);
    }

}
