package ru.kosmos.restaurantvoting.web.controllers.menues;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ru.kosmos.restaurantvoting.model.Menu;
import ru.kosmos.restaurantvoting.service.MenuService;

import static ru.kosmos.restaurantvoting.util.validation.ValidationUtil.checkNew;

@Slf4j
public abstract class AbstractMenuRestController {

    @Autowired
    private MenuService menuService;

    public Menu getWithDishes(int id) {
        log.info("get menu {}", id);
        return menuService.getWithDishes(id);
    }

    public Menu create(Menu menu) {
        log.info("create menu {}", menu);
        checkNew(menu);
        return menuService.create(menu);
    }

}