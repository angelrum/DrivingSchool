package ru.project.drivingschool.service;

import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.project.drivingschool.model.Employee;
import ru.project.drivingschool.testdata.EmployeeTestData;
import java.util.List;

import static ru.project.drivingschool.testdata.EmployeeTestData.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeServiceTest extends AbstractServiceTest<Employee> {

    private EmployeeService service;

    private EmployeeTestData testData;

    @Autowired
    public EmployeeServiceTest(EmployeeService service) {
        super(service, new EmployeeTestData(), EMPLOYEE_MATCHER);
        this.service = service;
        this.testData = new EmployeeTestData();
    }

    //что бы сравнивать schools необходимо из сравнения исключить поля createdBy и createdOn
    //это будет сделано после реализации тестов для ServiceSchools
    @Override
    void getAll() {
        super.getAll();
        List<Employee> actual = service.getAll();
        for (int i = 0; i < actual.size(); i++)
            Assertions.assertThat(actual.get(i).getSchools())
                    .hasSize(testData.getAll().get(i).getSchools().size());
    }

}