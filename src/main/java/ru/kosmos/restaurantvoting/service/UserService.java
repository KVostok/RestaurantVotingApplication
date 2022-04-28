package ru.kosmos.restaurantvoting.service;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.kosmos.restaurantvoting.dto.UsersDTO;
import ru.kosmos.restaurantvoting.error.UpdateRestrictionException;
import ru.kosmos.restaurantvoting.model.BaseEntity;
import ru.kosmos.restaurantvoting.model.Users;
import ru.kosmos.restaurantvoting.repository.UserRepository;
import ru.kosmos.restaurantvoting.util.UsersUtil;

import java.util.List;

import static ru.kosmos.restaurantvoting.util.UsersUtil.PASSWORD_ENCODER;
import static ru.kosmos.restaurantvoting.util.UsersUtil.prepareToSave;
import static ru.kosmos.restaurantvoting.util.validation.ValidationUtil.checkNotFound;
import static ru.kosmos.restaurantvoting.util.validation.ValidationUtil.checkNotFoundWithId;

@Service("userService")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserService {

    private final UserRepository repository;

    private boolean modificationRestriction = true;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public Users create(Users users) {
        Assert.notNull(users, "user must not be null");
        return prepareAndSave(users);
    }

    public void delete(int id) {
        checkModificationAllowed(id);
        checkNotFoundWithId(repository.delete(id), id);
    }

    public Users get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public Users getByIdWithRoles(int id) {
        return checkNotFoundWithId(repository.getByIdWithRoles(id), id);
    }

    public Users getByEmail(String email) {
        Assert.notNull(email, "email must not be null");
        return checkNotFound(repository.getByEmail(email).orElse(null), "email=" + email);
    }

    public List<Users> getAll() {
        return repository.getAll();
    }

    @Transactional
    public void enable(int id, boolean enabled) {
        checkModificationAllowed(id);
        Users users = get(id);
        users.setEnabled(enabled);
    }

    public void update(Users users) {
        Assert.notNull(users, "user must not be null");
        checkModificationAllowed(users.id());
        prepareAndSave(users);
    }

    @Transactional
    public void update(UsersDTO usersDto) {
        checkModificationAllowed(usersDto.id());
        Users users = get(usersDto.id());
        prepareAndSave(UsersUtil.updateFromTo(users, usersDto));
    }

    private Users prepareAndSave(Users users) {
        return repository.save(prepareToSave(users, PASSWORD_ENCODER));
    }

    protected void checkModificationAllowed(int id) {
        if (modificationRestriction && id <= BaseEntity.START_SEQ + 10) {
            throw new UpdateRestrictionException();
        }
    }

    public void switchOfModificationRestriction() {
        this.modificationRestriction = false;
    }

    public void switchOnModificationRestriction() {
        this.modificationRestriction = true;
    }

}
