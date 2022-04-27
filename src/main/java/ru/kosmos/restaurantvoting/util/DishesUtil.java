package ru.kosmos.restaurantvoting.util;

import lombok.experimental.UtilityClass;
import ru.kosmos.restaurantvoting.dto.DishesDTO;
import ru.kosmos.restaurantvoting.model.Dish;
import ru.kosmos.restaurantvoting.model.Dishes;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@UtilityClass
public class DishesUtil {

    public static Dishes asEntity(DishesDTO dishesDto) {
        Dish dish = new Dish();
        dish.setId(dishesDto.getId());
        return new Dishes(dish, dishesDto.getPrice());
    }

    public static Set<Dishes> getSetOfEntity(List<DishesDTO> listOfDto) {
        return listOfDto
                .stream()
                .map(DishesUtil::asEntity)
                .collect(Collectors.toSet());
    }

}
