package ru.kosmos.restaurantvoting.web.controllers.votes;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.kosmos.restaurantvoting.model.Votes;

import java.net.URI;
import java.time.LocalTime;

import static ru.kosmos.restaurantvoting.util.validation.ValidationUtil.TIME_CONSTRAINT;

@RestController
@RequestMapping(value = VoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Secured({"ROLE_ADMIN", "ROLE_USER"})
public class VoteRestController extends AbstractVoteRestController {

    static final String REST_URL = "/api/votes";

    //https://stackoverflow.com/questions/28245362/swagger-415-unsupported-media-type-application-json-instead-of-text-plain-in-po
    @PostMapping
    public ResponseEntity<Votes> createWithLocation(@RequestParam Integer menuId) {
        Votes created = super.create(menuId, LocalTime.now(), TIME_CONSTRAINT);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

}