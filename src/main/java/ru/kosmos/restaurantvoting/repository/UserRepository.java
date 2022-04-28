package ru.kosmos.restaurantvoting.repository;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.kosmos.restaurantvoting.model.Users;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    private static final Sort SORT_ID = Sort.by(Sort.Direction.ASC, "id");

    private final CrudUserRepository repository;

    public UserRepository(CrudUserRepository repository) {
        this.repository = repository;
    }

    public Users save(Users user) {
        return repository.save(user);
    }

    public boolean delete(int id) {
        return repository.delete(id) != 0;
    }

    public Users get(int id) {
        return repository.findById(id).orElse(null);
    }

    public Users getByIdWithRoles(int id) {
        return repository.getByIdWithRoles(id);
    }

    public Optional<Users> getByEmail(String email) {
        return repository.getByEmail(email);
    }

    public List<Users> getAll() {
        return repository.findAll(SORT_ID);
    }

}
