package ru.kosmos.restaurantvoting.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.kosmos.restaurantvoting.TimingExtension;
import ru.kosmos.restaurantvoting.model.Menu;
import ru.kosmos.restaurantvoting.model.Users;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.kosmos.restaurantvoting.testdata.MenuTestData.*;
import static ru.kosmos.restaurantvoting.testdata.UsersTestData.jsonWithPassword;
import static ru.kosmos.restaurantvoting.testdata.UsersTestData.user1;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(TimingExtension.class)
class JsonUtilTest {

    @Test
    void readWriteValue() {
        String json = JsonUtil.writeValue(menu);
        System.out.println(json);
        Menu menu_expected = JsonUtil.readValue(json, Menu.class);
        MATCHER.assertMatch(menu_expected, menu);
    }

    @Test
    void readWriteValues() {
        String json = JsonUtil.writeValue(menues);
        System.out.println(json);
        List<Menu> menues_expected = JsonUtil.readValues(json, Menu.class);
        MATCHER.assertMatch(menues_expected, menues);
    }

    @Test
    void writeOnlyAccess() {
        String json = JsonUtil.writeValue(user1);
        System.out.println(json);
        assertThat(json, not(containsString("password")));
        String jsonWithPass = jsonWithPassword(user1, "newPass");
        System.out.println(jsonWithPass);
        Users users = JsonUtil.readValue(jsonWithPass, Users.class);
        assertEquals(users.getPassword(), "newPass");
    }

}