package ru.project.drivingschool.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import ru.project.drivingschool.testdata.CompanyTestData;

import static org.junit.jupiter.api.Assertions.*;
import static ru.project.drivingschool.testdata.CompanyTestData.*;


@SpringBootTest
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
    }

    @Test
    void save() {
    }

    @Test
    void update() {
    }
}