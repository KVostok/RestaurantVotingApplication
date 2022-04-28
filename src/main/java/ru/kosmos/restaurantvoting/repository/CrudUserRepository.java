package ru.kosmos.restaurantvoting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.kosmos.restaurantvoting.model.Users;

import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudUserRepository extends JpaRepository<Users, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Users u WHERE u.id=:id")
    int delete(@Param("id") int id);

    @Query("from Users u left join fetch u.roles r left join fetch r.role where u.email =:email")
    Optional<Users> getByEmail(@Param("email") String email);

    @Query("from Users u left join fetch u.roles r left join fetch r.role where u.id =:id")
    Users getByIdWithRoles(@Param("id") int id);

}
