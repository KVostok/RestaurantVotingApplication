package ru.kosmos.restaurantvoting.web.controllers.votes;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ru.kosmos.restaurantvoting.model.Votes;
import ru.kosmos.restaurantvoting.service.VoteService;
import ru.kosmos.restaurantvoting.web.SecurityUtil;

import java.time.LocalTime;

@Slf4j
public abstract class AbstractVoteRestController {

    @Autowired
    private VoteService voteService;

    public Votes create(int menuId, LocalTime current, LocalTime constraint) {
        int userId = SecurityUtil.authUserId();
        log.info("create vote of user with id {} for menu with id {}", userId, menuId);
        return voteService.create(userId, menuId, current, constraint);
    }

}