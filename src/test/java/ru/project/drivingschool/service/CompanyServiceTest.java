package ru.project.drivingschool.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import ru.project.drivingschool.TimingExtension;
import ru.project.drivingschool.model.Company;
import ru.project.drivingschool.util.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;

import static ru.project.drivingschool.testdata.CompanyTestData.*;


@SpringBootTest
@ExtendWith(TimingExtension.class)
@Sql(scripts = "classpath:db/data-test.sql", config = @SqlConfig(encoding = "UTF-8"))
class CompanyServiceTest {

    @Autowired
    protected CompanyService service;

    @Test
    void getAll() {
        COMPANY_MATCHER.assertMatch(service.getAll(), COMPANYS);
    }

    @Test
    void get() {
        Company company = service.get(COMPANY_ID1);
        COMPANY_MATCHER.assertMatch(COMPANY1, company);
    }

    @Test
    void create() {
        Company company = getNew();
        service.create(company);
        List<Company> list = new ArrayList<>(COMPANYS);
        list.add(company);
        COMPANY_MATCHER.assertMatch(service.getAll(), list);
    }

    @Test
    void update() {
        Company company = getUpdate();
        Company upd = service.update(company);
        COMPANY_MATCHER.assertMatch(upd, company);
    }

    @Test
    void delete() {
        COMPANY_MATCHER.assertMatch(service.getAll(), COMPANYS);
        service.delete(COMPANY_ID1);
        COMPANY_MATCHER.assertMatch(service.getAll(), List.of(COMPANY2));
    }

    @Test
    void deleteNotFoundException() {
        Assertions.assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND_ID));
    }

    @Test
    void updateNotFoundException() {
        Company upd = getUpdate();
        upd.setId(NOT_FOUND_ID);
        Assertions.assertThrows(NotFoundException.class, () -> service.update(upd));
    }
}