package ru.kosmos.restaurantvoting.util;

import lombok.experimental.UtilityClass;
import ru.kosmos.restaurantvoting.dto.MenuDTO;
import ru.kosmos.restaurantvoting.model.Menu;
import ru.kosmos.restaurantvoting.model.Restaurant;

import static ru.kosmos.restaurantvoting.util.DishesUtil.getSetOfEntity;

@UtilityClass
public class MenuUtil {

    public static Menu asEntity(MenuDTO menuDto) {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(menuDto.getId());
        return new Menu(restaurant, getSetOfEntity(menuDto.getDishes()));
    }

}
