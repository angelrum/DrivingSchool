package ru.project.drivingschool.testdata;

import ru.project.drivingschool.model.common.AbstractKeyHistoryEntity;
import ru.project.drivingschool.model.common.HasId;

import java.util.Collection;

public interface TestDataInterface <T extends AbstractKeyHistoryEntity> {

    long getId1();

    long getId2();

    Collection<T> getAll();

    T getNew();

    T getUpdate();

    default T getObjectById(long id) {
        return getAll().stream()
                .filter(t -> t.id() == id)
                .findFirst().orElse(null);
    }

}
