package ru.kosmos.restaurantvoting.testdata;

import ru.kosmos.restaurantvoting.MatcherFactory;
import ru.kosmos.restaurantvoting.model.Dish;

import java.util.List;

import static ru.kosmos.restaurantvoting.model.BaseEntity.START_SEQ;

public class DishTestData {

    public static final MatcherFactory.Matcher<Dish> MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Dish.class, "dishes");

    public static final int DISH_ID = START_SEQ;

    public static final Dish dish1 = new Dish(DISH_ID, "Салат");
    public static final Dish dish2 = new Dish(DISH_ID + 1, "Суп");
    public static final Dish dish3 = new Dish(DISH_ID + 2, "Гарнир");
    public static final Dish dish4 = new Dish(DISH_ID + 3, "Котлета");
    public static final Dish dish5 = new Dish(DISH_ID + 4, "Булочка");
    public static final Dish dish6 = new Dish(DISH_ID + 5, "Компот");
    public static final Dish dish7 = new Dish(DISH_ID + 6, "Чай");
    public static final Dish dish8 = new Dish(DISH_ID + 7, "Кофе");

    public static final List<Dish> dishes = List.of(dish1, dish2, dish3, dish4, dish5, dish6, dish7, dish8);

    public static Dish getNewDish() {
        return new Dish(null, "Пельмени");
    }

    public static Dish getUpdatedDish() {
        return new Dish(DISH_ID, "Борщ");
    }

}
