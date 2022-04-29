package ru.kosmos.restaurantvoting.web.controllers.menues;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.kosmos.restaurantvoting.dto.MenuDTO;
import ru.kosmos.restaurantvoting.model.Menu;

import javax.validation.Valid;
import java.net.URI;

import static ru.kosmos.restaurantvoting.util.MenuUtil.asEntity;

@RestController
@RequestMapping(value = MenuRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Secured("ROLE_ADMIN")
public class MenuRestController extends AbstractMenuRestController {

    static final String REST_URL = "/api/menues";

    @GetMapping("/{id}")
    public Menu getWithDishes(@PathVariable int id) {
        return super.getWithDishes(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> createWithLocation(@Valid @RequestBody MenuDTO menuDto) {
        Menu created = super.create(asEntity(menuDto));
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

}