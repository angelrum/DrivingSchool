package ru.project.drivingschool.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import ru.project.drivingschool.TimingExtension;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(TimingExtension.class)
@Sql(scripts = "classpath:db/data-test.sql", config = @SqlConfig(encoding = "UTF-8"))
class EmployeeServiceTest {

    @Autowired
    protected EmployeeService service;

    @Test
    void getAll() {
        service.getAll().forEach(System.out::println);

    }

}