package ru.kosmos.restaurantvoting.testdata;

import ru.kosmos.restaurantvoting.MatcherFactory;
import ru.kosmos.restaurantvoting.model.Votes;

import static ru.kosmos.restaurantvoting.model.BaseEntity.START_SEQ;

public class VotesTestData {

    public static final MatcherFactory.Matcher<Votes> MATCHER = MatcherFactory.usingEqualsComparator(Votes.class);

    public static final int VOTES_ID = START_SEQ;

}
