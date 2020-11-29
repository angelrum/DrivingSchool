package ru.project.drivingschool.repository;

import ru.project.drivingschool.model.AbstractHistoryEntity;
import ru.project.drivingschool.repository.jpa.JpaUserRepository;

import java.time.LocalDateTime;
import java.util.Objects;

public interface InterfaceHistoryRepository <T extends AbstractHistoryEntity> {

    default T save(T t, Long createdBy, boolean isNew) {
        var created = (Objects.nonNull(createdBy) && createdBy > 0L) ? getUserRepository().getOne(createdBy) : null;
        var now = LocalDateTime.now();
        var h = t.getHistory();
        if (isNew)
            h.setCreatedBy(created);
        else {
            h.setChangedBy(created);
            h.setChangedOn(now);
        }
        return save(t);
    }

    T save(T t);

    JpaUserRepository getUserRepository();
}
