package ru.kosmos.restaurantvoting.testdata;

import ru.kosmos.restaurantvoting.MatcherFactory;
import ru.kosmos.restaurantvoting.model.Dishes;
import ru.kosmos.restaurantvoting.model.Menu;
import ru.kosmos.restaurantvoting.model.Votes;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.kosmos.restaurantvoting.model.BaseEntity.START_SEQ;
import static ru.kosmos.restaurantvoting.testdata.DishTestData.*;
import static ru.kosmos.restaurantvoting.testdata.UsersTestData.*;
import static ru.kosmos.restaurantvoting.testdata.VotesTestData.VOTES_ID;

public class MenuTestData {

    public static final MatcherFactory.Matcher<Menu> MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Menu.class, "restaurant", "votes");
    public static final MatcherFactory.Matcher<Menu> MATCHER_EASY = MatcherFactory.usingIgnoringFieldsComparator(Menu.class, "restaurant", "votes", "dishes");
    public static MatcherFactory.Matcher<Menu> WITH_DISHES_MATCHER =
            MatcherFactory.usingAssertions(Menu.class,
                    (a, e) -> assertThat(a).usingRecursiveComparison()
                            .ignoringFields("restaurant", "votes", "dishes.menu", "dishes.dish.dishes", "dishes.id").isEqualTo(e),
                    (a, e) -> {
                        throw new UnsupportedOperationException();
                    });

    public static final int MENU_ID = START_SEQ;
    public static final int DISHES_ID = START_SEQ;

    public static final Dishes dishes1 = new Dishes(DISHES_ID, 40, dish1);
    public static final Dishes dishes2 = new Dishes(DISHES_ID + 1, 100, dish2);
    public static final Dishes dishes3 = new Dishes(DISHES_ID + 2, 50, dish3);
    public static final Dishes dishes4 = new Dishes(DISHES_ID + 3, 20, dish4);
    public static final Dishes dishes5 = new Dishes(DISHES_ID + 4, 20, dish5);
    public static final Dishes dishes6 = new Dishes(DISHES_ID + 5, 10, dish6);

    public static final Menu menu = new Menu(MENU_ID, LocalDate.now(), Set.of(dishes1, dishes2, dishes3, dishes4,
            dishes5, dishes6));
    public static final Menu menu1 = new Menu(MENU_ID + 1, LocalDate.now());

    public static final Votes vote1 = new Votes(VOTES_ID, menu, user1);
    public static final Votes vote2 = new Votes(VOTES_ID + 1, menu, user2);
    public static final Votes vote3 = new Votes(VOTES_ID + 2, menu, user3);
    public static final Votes vote4 = new Votes(VOTES_ID + 3, menu, user4);
    public static final Votes vote5 = new Votes(VOTES_ID + 4, menu, user5);

    public static final List<Menu> menues = List.of(menu, menu);

    static {
        menu.setVotes(Set.of(vote1, vote2, vote3, vote4, vote5));
    }

}
