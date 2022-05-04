package ru.kosmos.restaurantvoting.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.kosmos.restaurantvoting.error.NotFoundException;
import ru.kosmos.restaurantvoting.error.VoteCantBeChangedException;
import ru.kosmos.restaurantvoting.model.Votes;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.kosmos.restaurantvoting.testdata.MenuTestData.*;
import static ru.kosmos.restaurantvoting.testdata.UsersTestData.*;
import static ru.kosmos.restaurantvoting.testdata.VotesTestData.MATCHER;
import static ru.kosmos.restaurantvoting.testdata.VotesTestData.VOTES_ID;

class VoteServiceTest extends AbstractServiceTest {

    @Autowired
    protected VoteService service;

    @Test
    void createNewVoteBeforeConstraint() {
        Votes created = service.create(USER_ID + 11, MENU_ID, LocalTime.now(), LocalTime.now().plusMinutes(5));
        int newId = created.id();
        Votes newVote = new Votes(menu, user12);
        newVote.setId(newId);
        MATCHER.assertMatch(created, newVote);
        MATCHER.assertMatch(service.getByIdWithMenuWithUser(newId), newVote);
    }

    @Test
    void createNewVoteAfterConstraint() {
        Votes created = service.create(USER_ID + 12, MENU_ID, LocalTime.now(), LocalTime.now().minusMinutes(5));
        int newId = created.id();
        Votes newVote = new Votes(menu, user12);
        newVote.setId(newId);
        MATCHER.assertMatch(created, newVote);
        MATCHER.assertMatch(service.getByIdWithMenuWithUser(newId), newVote);
    }

    @Test
    void changeVoteBeforeConstraint() {
        Votes created = service.create(USER_ID, MENU_ID + 1, LocalTime.now(), LocalTime.now().plusMinutes(5));
        int newId = created.id();
        Votes newVote = new Votes(menu1, user1);
        newVote.setId(newId);
        MATCHER.assertMatch(created, newVote);
        MATCHER.assertMatch(service.getByIdWithMenuWithUser(newId), newVote);
    }

    @Test
    void changeVoteAfterConstraint() {
        assertThrows(VoteCantBeChangedException.class,
                () -> service.create(USER_ID, MENU_ID + 1, LocalTime.now(), LocalTime.now().minusMinutes(5)));
    }

    @Test
    void getByIdWithMenuWithUser() {
        Votes votes = service.getByIdWithMenuWithUser(VOTES_ID);
        MATCHER.assertMatch(votes, vote1);
    }

    @Test
    void getByIdWithMenuWithUserNotFound() {
        assertThrows(NotFoundException.class, () -> service.getByIdWithMenuWithUser(NOT_FOUND));
    }

}