package ru.project.drivingschool.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import ru.project.drivingschool.TestMatcher;
import ru.project.drivingschool.TimingExtension;
import ru.project.drivingschool.model.HasId;
import ru.project.drivingschool.testdata.TestDataInterface;
import ru.project.drivingschool.util.exception.NotFoundException;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(TimingExtension.class)
@Sql(scripts = "classpath:db/data-test.sql", config = @SqlConfig(encoding = "UTF-8"))
abstract class AbstractServiceTest <T extends HasId> {

    private AbstractService<T> service;

    private TestDataInterface<T> testData;

    private TestMatcher<T> matcher;

    private static final long NOT_FOUND_ID = 9999L;

    AbstractServiceTest(AbstractService<T> service, TestDataInterface<T> testData, TestMatcher<T> matcher) {
        this.service = service;
        this.testData = testData;
        this.matcher = matcher;
    }

    @Test
    void getAll() {
        matcher.assertMatch(service.getAll(), testData.getAll());
    }

    @Test
    void get() {
        long id = testData.getId1();
        matcher.assertMatch(service.get(id), testData.getObjectById(id));
    }

    @Test
    void create() {
        T t = testData.getNew();
        T create = service.create(t);
        t.setId(create.getId());
        Collection<T> collection = testData.getAll();
        collection.add(t);
        matcher.assertMatch(service.get(t.id()), t);
        matcher.assertMatch(service.getAll(), collection);
    }

    @Test
    void delete() {
        long id = testData.getId1();
        service.delete(id);
        Collection<T> collection = testData.getAll();
        collection.removeIf(t->t.id() == id);
        matcher.assertMatch(service.getAll(), collection);
    }

    @Test
    void update() {
        T upd = testData.getUpdate();
        service.update(upd);
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
        assertThrows(NotFoundException.class, ()->service.update(upd));

    }
}
