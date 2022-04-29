package ru.kosmos.restaurantvoting.testdata;

import ru.kosmos.restaurantvoting.MatcherFactory;
import ru.kosmos.restaurantvoting.model.Role;
import ru.kosmos.restaurantvoting.model.Roles;
import ru.kosmos.restaurantvoting.model.Users;
import ru.kosmos.restaurantvoting.util.JsonUtil;

import java.util.Date;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.kosmos.restaurantvoting.model.BaseEntity.START_SEQ;

public class UsersTestData {

    public static final MatcherFactory.Matcher<Users> MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Users.class, "registered", "password", "votes", "roles");
    public static MatcherFactory.Matcher<Users> WITH_ROLES_MATCHER =
            MatcherFactory.usingAssertions(Users.class,
                    (a, e) -> assertThat(a).usingRecursiveComparison()
                            .ignoringFields("registered", "password", "votes", "roles.user", "roles.role.roles").isEqualTo(e),
                    (a, e) -> {
                        throw new UnsupportedOperationException();
                    });

    public static final int USER_ID = START_SEQ + 1;
    public static final int ADMIN_ID = START_SEQ;
    public static final int NOT_FOUND = 10;
    public static final int USER_ROLE_ID = START_SEQ + 1;
    public static final int ADMIN_ROLE_ID = START_SEQ;
    public static final int USER_ROLES_ID = START_SEQ + 2;
    public static final int ADMIN_ROLES_ID_1 = START_SEQ;
    public static final int ADMIN_ROLES_ID_2 = START_SEQ + 1;

    public static final Users user1 = new Users(USER_ID, "User1", "user1@yandex.ru", "password");
    public static final Users user2 = new Users(USER_ID + 1, "User2", "user2@yandex.ru", "password");
    public static final Users user3 = new Users(USER_ID + 2, "User3", "user3@yandex.ru", "password");
    public static final Users user4 = new Users(USER_ID + 3, "User4", "user4@yandex.ru", "password");
    public static final Users user5 = new Users(USER_ID + 4, "User5", "user5@yandex.ru", "password");
    public static final Users user6 = new Users(USER_ID + 5, "User6", "user6@yandex.ru", "password");
    public static final Users user7 = new Users(USER_ID + 6, "User7", "user7@yandex.ru", "password");
    public static final Users user8 = new Users(USER_ID + 7, "User8", "user8@yandex.ru", "password");
    public static final Users user9 = new Users(USER_ID + 8, "User9", "user9@yandex.ru", "password");
    public static final Users user10 = new Users(USER_ID + 9, "User10", "user10@yandex.ru", "password");
    public static final Users user11 = new Users(USER_ID + 10, "User11", "user11@yandex.ru", "password");
    public static final Users user12 = new Users(USER_ID + 11, "User12", "user12@yandex.ru", "password");
    public static final Users user13 = new Users(USER_ID + 12, "User13", "user13@yandex.ru", "password");
    public static final Users user14 = new Users(USER_ID + 13, "User14", "user14@yandex.ru", "password");
    public static final Users user15 = new Users(USER_ID + 14, "User15", "user15@yandex.ru", "password");
    public static final Users user16 = new Users(USER_ID + 15, "User16", "user16@yandex.ru", "password");
    public static final Users user17 = new Users(USER_ID + 16, "User17", "user17@yandex.ru", "password");
    public static final Users user18 = new Users(USER_ID + 17, "User18", "user18@yandex.ru", "password");
    public static final Users user19 = new Users(USER_ID + 18, "User19", "user19@yandex.ru", "password");
    public static final Users user20 = new Users(USER_ID + 19, "User20", "user20@yandex.ru", "password");
    public static final Users admin = new Users(ADMIN_ID, "Admin", "admin@gmail.com", "admin");
    public static final Role userRole = new Role(USER_ROLE_ID, "USER");
    public static final Role adminRole = new Role(ADMIN_ROLE_ID, "ADMIN");
    public static final Roles userRoles = new Roles(USER_ROLES_ID, user1, userRole);
    public static final Roles adminRoles1 = new Roles(ADMIN_ROLES_ID_1, admin, adminRole);
    public static final Roles adminRoles2 = new Roles(ADMIN_ROLES_ID_2, admin, userRole);

    static {
        user1.setRoles(Set.of(userRoles));
        admin.setRoles(Set.of(adminRoles1, adminRoles2));
    }

    public static Users getNewUser() {
        Users user = new Users(null, "New", "new@gmail.com", "newPass", false, new Date());
        user.setRoles(Set.of(new Roles(null, user, userRole)));
        return user;
    }

    public static Users getUpdatedUser() {
        Users user = new Users(USER_ID, "UpdatedName", "user@yandex.ru", "newPass", false, new Date());
        user.setRoles(Set.of(userRoles));
        return user;
    }

    public static String jsonWithPassword(Users users, String password) {
        return JsonUtil.writeAdditionProps(users, "password", password);
    }

}
