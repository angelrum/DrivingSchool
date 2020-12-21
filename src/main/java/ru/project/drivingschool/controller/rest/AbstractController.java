package ru.project.drivingschool.controller.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.project.drivingschool.AuthorizedUser;
import ru.project.drivingschool.View;
import ru.project.drivingschool.model.common.AbstractKeyHistoryEntity;
import ru.project.drivingschool.service.AbstractService;

public abstract class AbstractController <T extends AbstractKeyHistoryEntity, To> {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private AbstractService<T> service;

    public AbstractController(AbstractService<T> service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public To get(@PathVariable Long id) {
        log.info("get by id {} and company {}", id, AuthorizedUser.getCompanyId());
        return createTo(service.get(id));
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void create(@Validated(View.Web.class) T t) {
        log.info("create object {}", t.toString());
        service.create(t, AuthorizedUser.getId());
    }

    @PutMapping
    public void update(@Validated(View.Web.class) T t) {
        log.info("update employee {}", t.toString());
        service.update(t, AuthorizedUser.getId());
    }

    abstract To createTo(T t);

}
