package ru.kosmos.restaurantvoting.web.controllers.menues;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.kosmos.restaurantvoting.dto.DishesDTO;
import ru.kosmos.restaurantvoting.dto.MenuDTO;
import ru.kosmos.restaurantvoting.model.Menu;
import ru.kosmos.restaurantvoting.service.MenuService;
import ru.kosmos.restaurantvoting.util.JsonUtil;
import ru.kosmos.restaurantvoting.util.MenuUtil;
import ru.kosmos.restaurantvoting.web.AbstractControllerTest;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.kosmos.restaurantvoting.testdata.MenuTestData.*;
import static ru.kosmos.restaurantvoting.testdata.RestaurantTestData.REST_ID;
import static ru.kosmos.restaurantvoting.testdata.UsersTestData.*;

class MenuRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = MenuRestController.REST_URL + '/';

    @Autowired
    private MenuService menuService;

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getWithDishes() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + MENU_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(WITH_DISHES_MATCHER.contentJson(menu));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + NOT_FOUND))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getAccessDenied() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + REST_ID))
                .andExpect(status().isForbidden())
                .andDo(print());
    }

    @Test
    void getUnAuth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void createWithLocation() throws Exception {
        MenuDTO newDto = new MenuDTO(REST_ID + 5, List.of(new DishesDTO(10000, 10),
                new DishesDTO(10001, 20)));
        Menu newMenu = MenuUtil.asEntity(newDto);
        newMenu.setDate(LocalDate.now());
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newDto)))
                .andDo(print())
                .andExpect(status().isCreated());

        Menu created = WITH_DISHES_MATCHER.readFromJson(action);
        int newId = created.id();
        newMenu.setId(newId);
        WITH_DISHES_MATCHER.assertMatch(created, newMenu);
        WITH_DISHES_MATCHER.assertMatch(menuService.getWithDishes(newId), newMenu);
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void createInvalid() throws Exception {
        MenuDTO invalid = new MenuDTO(null, null);
        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void createInvalidNoPrice() throws Exception {
        MenuDTO invalid = new MenuDTO(REST_ID + 5, List.of(new DishesDTO(10000, null),
                new DishesDTO(10001, null)));
        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid)))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void createDuplicate() throws Exception {
        MenuDTO invalid = new MenuDTO(REST_ID, List.of(new DishesDTO(10000, 10),
                new DishesDTO(10001, 20)));
        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid)))
                .andDo(print())
                .andExpect(status().isConflict());
    }

}