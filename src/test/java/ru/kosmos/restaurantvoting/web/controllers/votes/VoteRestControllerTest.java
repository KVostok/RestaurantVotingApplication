package ru.kosmos.restaurantvoting.web.controllers.votes;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.kosmos.restaurantvoting.model.Votes;
import ru.kosmos.restaurantvoting.service.VoteService;
import ru.kosmos.restaurantvoting.testdata.VotesTestData;
import ru.kosmos.restaurantvoting.util.validation.ValidationUtil;
import ru.kosmos.restaurantvoting.web.AbstractControllerTest;

import java.time.LocalTime;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.kosmos.restaurantvoting.testdata.MenuTestData.menu;
import static ru.kosmos.restaurantvoting.testdata.UsersTestData.*;

class VoteRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = VoteRestController.REST_URL + '/';

    @Autowired
    private VoteService service;

    @Test
    void getUnAuth() throws Exception {
        perform(MockMvcRequestBuilders.post(REST_URL))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @Test
    @WithUserDetails(value = USER12_MAIL)
    void createWithLocationNewVoteBeforeConstraint() throws Exception {
        ValidationUtil.TIME_CONSTRAINT = LocalTime.now().plusMinutes(5);
        Votes newVote = new Votes(menu, user12);
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .param("menuId", "10000"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        newVote.setId(VotesTestData.MATCHER.readFromJson(action).id());
        Votes created = service.getByIdWithMenuWithUser(newVote.id());
        VotesTestData.MATCHER.assertMatch(created, newVote);
        ValidationUtil.TIME_CONSTRAINT = LocalTime.of(11, 0, 0);
    }

    @Test
    @WithUserDetails(value = USER13_MAIL)
    void createWithLocationNewVoteAfterConstraint() throws Exception {
        ValidationUtil.TIME_CONSTRAINT = LocalTime.now().minusMinutes(5);
        Votes newVote = new Votes(menu, user13);
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .param("menuId", "10000"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        newVote.setId(VotesTestData.MATCHER.readFromJson(action).id());
        Votes created = service.getByIdWithMenuWithUser(newVote.id());
        VotesTestData.MATCHER.assertMatch(created, newVote);
        ValidationUtil.TIME_CONSTRAINT = LocalTime.of(11, 0, 0);
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void createWithLocationChangeVoteBeforeConstraint() throws Exception {
        ValidationUtil.TIME_CONSTRAINT = LocalTime.now().plusMinutes(5);
        perform(MockMvcRequestBuilders.post(REST_URL)
                .param("menuId", "10001"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        ValidationUtil.TIME_CONSTRAINT = LocalTime.of(11, 0, 0);
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void createWithLocationChangeVoteAfterConstraint() throws Exception {
        ValidationUtil.TIME_CONSTRAINT = LocalTime.now().minusMinutes(5);
        perform(MockMvcRequestBuilders.post(REST_URL)
                .param("menuId", "10001"))
                .andDo(print())
                .andExpect(status().isInternalServerError());

        ValidationUtil.TIME_CONSTRAINT = LocalTime.of(11, 0, 0);
    }

}