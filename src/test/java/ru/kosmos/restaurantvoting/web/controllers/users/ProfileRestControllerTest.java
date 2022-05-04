package ru.kosmos.restaurantvoting.web.controllers.users;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.kosmos.restaurantvoting.dto.UsersDTO;
import ru.kosmos.restaurantvoting.model.Users;
import ru.kosmos.restaurantvoting.service.UserService;
import ru.kosmos.restaurantvoting.util.JsonUtil;
import ru.kosmos.restaurantvoting.util.UsersUtil;
import ru.kosmos.restaurantvoting.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.kosmos.restaurantvoting.testdata.UsersTestData.*;
import static ru.kosmos.restaurantvoting.web.controllers.users.ProfileRestController.REST_URL;

class ProfileRestControllerTest extends AbstractControllerTest {

    @Autowired
    private UserService userService;

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getByIdWithRoles() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentJson(user1))
                .andDo(print());
    }

    @Test
    void getUnAuth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void delete() throws Exception {
        userService.switchOfModificationRestriction();
        perform(MockMvcRequestBuilders.delete(REST_URL))
                .andExpect(status().isNoContent())
                .andDo(print());
        userService.switchOnModificationRestriction();
        MATCHER.assertMatch(userService.getAll(), admin, user2, user3, user4, user5, user6, user7, user8, user9,
                user10, user11, user12, user13, user14, user15, user16, user17, user18, user19, user20);
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void update() throws Exception {
        UsersDTO updatedDto = new UsersDTO(null, "newName", "user1@yandex.ru", "newPassword");
        userService.switchOfModificationRestriction();

        perform(MockMvcRequestBuilders.put(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updatedDto)))
                .andDo(print())
                .andExpect(status().isNoContent());
        userService.switchOnModificationRestriction();
        MATCHER.assertMatch(userService.get(USER_ID), UsersUtil.updateFromTo(new Users(user1), updatedDto));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void updateInvalid() throws Exception {
        UsersDTO updatedDto = new UsersDTO(null, null, "password", null);
        userService.switchOfModificationRestriction();
        perform(MockMvcRequestBuilders.put(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updatedDto)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
        userService.switchOnModificationRestriction();
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    @Transactional(propagation = Propagation.NEVER)
    void updateDuplicate() throws Exception {
        UsersDTO updatedDto = new UsersDTO(null, "newName", "admin@gmail.com", "newPassword");
        userService.switchOfModificationRestriction();
        perform(MockMvcRequestBuilders.put(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updatedDto)))
                .andDo(print())
                .andExpect(status().isConflict());
        userService.switchOnModificationRestriction();
    }

}