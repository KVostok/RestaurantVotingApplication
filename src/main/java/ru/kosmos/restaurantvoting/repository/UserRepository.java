package ru.kosmos.restaurantvoting.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kosmos.restaurantvoting.model.Users;

import java.util.Optional;

@Transactional(readOnly = true)
@Repository
public interface UserRepository extends BaseRepository<Users> {

    @Query("from Users u left join fetch u.roles r left join fetch r.role where u.email =:email")
    Optional<Users> getByEmail(@Param("email") String email);

}
