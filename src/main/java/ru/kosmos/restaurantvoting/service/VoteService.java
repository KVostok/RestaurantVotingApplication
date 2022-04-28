package ru.kosmos.restaurantvoting.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kosmos.restaurantvoting.error.VoteCantBeChangedException;
import ru.kosmos.restaurantvoting.model.Votes;
import ru.kosmos.restaurantvoting.repository.VoteRepository;
import ru.kosmos.restaurantvoting.util.DateTimeUtil;

import java.time.LocalTime;

import static ru.kosmos.restaurantvoting.util.validation.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteService {

    private final VoteRepository voteRepository;
    private final MenuService menuService;
    private final UserService userService;

    public VoteService(VoteRepository voteRepository, MenuService menuService, UserService userService) {
        this.voteRepository = voteRepository;
        this.menuService = menuService;
        this.userService = userService;
    }

    @Transactional
    public Votes create(int userId, int menuId, LocalTime current, LocalTime constraint) {
        Votes votes = voteRepository.getForUser(userId);

        if (votes == null)
            return voteRepository.save(new Votes(menuService.get(menuId), userService.get(userId)));
        if (!current.isBefore(constraint))
            throw new VoteCantBeChangedException("Vote can't be changed after " + DateTimeUtil.toString(constraint));

        votes.setMenu(menuService.get(menuId));
        return voteRepository.save(votes);
    }

    public Votes getByIdWithMenuWithUser(int id) {
        return checkNotFoundWithId(voteRepository.getByIdWithMenuWithUser(id), id);
    }
}
