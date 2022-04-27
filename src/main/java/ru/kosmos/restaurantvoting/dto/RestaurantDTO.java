package ru.kosmos.restaurantvoting.dto;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import ru.kosmos.restaurantvoting.model.Menu;

import java.beans.ConstructorProperties;
import java.util.Set;

@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RestaurantDTO extends NamedDTO {

    Set<Menu> menues;
    Integer rating;
    boolean isVoted;

    @ConstructorProperties({"id", "name", "menues", "rating", "isVoted"})
    public RestaurantDTO(Integer id, String name, Set<Menu> menues, Integer rating, boolean isVoted) {
        super(id, name);
        this.menues = menues;
        this.rating = rating;
        this.isVoted = isVoted;
    }

}
