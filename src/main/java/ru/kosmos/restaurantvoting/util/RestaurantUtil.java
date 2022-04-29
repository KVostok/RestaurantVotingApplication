package ru.kosmos.restaurantvoting.util;

import lombok.experimental.UtilityClass;
import ru.kosmos.restaurantvoting.dto.RestaurantDTO;
import ru.kosmos.restaurantvoting.model.Menu;
import ru.kosmos.restaurantvoting.model.Restaurant;
import ru.kosmos.restaurantvoting.model.Votes;
import ru.kosmos.restaurantvoting.web.SecurityUtil;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@UtilityClass
public class RestaurantUtil {

    public static List<RestaurantDTO> getDTOs(Collection<Restaurant> restaurants) {
        return restaurants.stream()
                .map(RestaurantUtil::createDTO)
                .toList();
    }

    public static RestaurantDTO createDTO(Restaurant restaurant) {
        Menu menu = restaurant.getMenues().stream().findFirst().orElse(null);
        if (menu == null || menu.getVotes() == null)
            return new RestaurantDTO(restaurant.getId(), restaurant.getName(), restaurant.getMenues(), 0, false);

        Set<Votes> votes = menu.getVotes();
        Integer countVotes = votes.size();
        boolean isVoted = votes.stream()
                .anyMatch(
                        votes1 -> {
                            int id = votes1.getUser().getId() == null ? 0 : votes1.getUser().getId();
                            return id == SecurityUtil.authUserId();
                        }
                );
        return new RestaurantDTO(restaurant.getId(), restaurant.getName(), restaurant.getMenues(), countVotes, isVoted);
    }

}
