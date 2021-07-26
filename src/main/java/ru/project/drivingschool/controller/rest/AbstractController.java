package ru.project.drivingschool.controller.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.project.drivingschool.model.Index;
import ru.project.drivingschool.model.common.AbstractKeyHistoryEntity;
import ru.project.drivingschool.security.SecurityUtil;
import ru.project.drivingschool.service.AbstractService;
import ru.project.drivingschool.to.BaseTo;
import ru.project.drivingschool.to.ResultPage;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class AbstractController <T extends AbstractKeyHistoryEntity, To> {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    private final AbstractService<T> service;

    public AbstractController(AbstractService<T> service) {
        this.service = service;
    }

    public ResultPage<To> get(Long id, Boolean active, Integer limit, Integer offset) {
        if (Objects.nonNull(id))
            return new ResultPage<>(new Index(), List.of(createTo(this.service.get(id))));

        boolean limitParam = Objects.isNull(limit) || Objects.isNull(offset);
        ResultPage<T> result = limitParam ? this.service.getAll(active)
                : this.service.getAll(offset, limit, active);

        return fromTtoTo(result);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody BaseTo create(@Valid @RequestBody To to) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        log.info("create object {}", to.toString());
        T t = convertTOinT(to);
        service.create(t, SecurityUtil.getInstance().authUserId());
        return new BaseTo(t.id());
    }

    @PutMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void update(@Valid @RequestBody To to) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        log.info("update employee {}", to.toString());
        T t = convertTOinT(to);
        service.update(t, SecurityUtil.getInstance().authUserId());
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        log.info("delete object by id={}", id);
        service.delete(id);
    }

    abstract To createTo(T t);

    abstract T convertTOinT(To to) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

    protected ResultPage<To> fromTtoTo(ResultPage<T> rp) {
        List<To> tos = rp.getResult().stream().map(this::createTo).collect(Collectors.toList());
        return new ResultPage<>(rp.getIndex(), tos);
    }
}
