package ru.kosmos.restaurantvoting.web;

import lombok.Getter;
import lombok.ToString;
import org.springframework.lang.NonNull;
import ru.kosmos.restaurantvoting.model.Users;

@Getter
@ToString(of = "user")
public class AuthUser extends org.springframework.security.core.userdetails.User {

    private final Users user;

    public AuthUser(@NonNull Users user) {
        super(user.getEmail(), user.getPassword(), user.getRoles());
        this.user = user;
    }

    public int id() {
        return user.id();
    }

}