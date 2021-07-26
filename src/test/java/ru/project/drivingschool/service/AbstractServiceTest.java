package ru.project.drivingschool.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import ru.project.drivingschool.AuthorizedUserTest;
import ru.project.drivingschool.TestMatcher;
import ru.project.drivingschool.TimingExtension;
import ru.project.drivingschool.model.Index;
import ru.project.drivingschool.model.common.AbstractKeyHistoryEntity;
import ru.project.drivingschool.model.common.HasId;
import ru.project.drivingschool.testdata.TestDataInterface;
import ru.project.drivingschool.util.exception.NotFoundException;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(TimingExtension.class)
@Sql(scripts = "classpath:db/data-test.sql", config = @SqlConfig(encoding = "UTF-8"))
abstract class AbstractServiceTest <T extends AbstractKeyHistoryEntity> {

    private AbstractService<T> service;

    private TestDataInterface<T> testData;

    private TestMatcher<T> matcher;

    protected static final long NOT_FOUND_ID = 9999L;

    AbstractServiceTest(AbstractService<T> service, TestDataInterface<T> testData, TestMatcher<T> matcher) {
        this.service = service;
        this.testData = testData;
        this.matcher = matcher;
    }

    @Test
    void getAll() {
        matcher.assertMatch(service.getAll().getResult(), testData.getAll());
    }

    @Test
    void get() {
        long id = testData.getId2();
        matcher.assertMatch(service.get(id), testData.getObjectById(id));
    }

    @Test
    void create() {
        T t = testData.getNew();
        T create = service.create(t, AuthorizedUserTest.getId());
        t.setId(create.getId());
        Collection<T> collection = testData.getAll();
        collection.add(t);
        matcher.assertMatch(service.get(t.id()), t);
        matcher.assertMatch(service.getAll().getResult(), collection);
    }

    @Test
    void delete() {
        long id = testData.getId1();
        service.delete(id);
        Collection<T> collection = testData.getAll();
        collection.removeIf(t->t.id() == id);
        matcher.assertMatch(service.getAll().getResult(), collection);
    }

    @Test
    void update() {
        T upd = testData.getUpdate();
        service.update(upd, AuthorizedUserTest.getId());
        matcher.assertMatch(service.get(upd.id()), upd);
    }

    @Test
    void getNotFoundId() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND_ID));
    }

    @Test
    void deleteNotFoundId() {
        assertThrows(NotFoundException.class, ()->service.delete(NOT_FOUND_ID));
    }

    @Test
    void updateNotFoundId() {
        T upd = testData.getUpdate();
        upd.setId(NOT_FOUND_ID);
        assertThrows(NotFoundException.class, ()->service.update(upd, AuthorizedUserTest.getId()));
    }
}
