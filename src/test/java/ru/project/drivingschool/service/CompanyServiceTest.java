package ru.project.drivingschool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.project.drivingschool.model.Company;
import ru.project.drivingschool.testdata.CompanyTestData;

@SpringBootTest
class CompanyServiceTest extends AbstractServiceTest<Company> {

    @Autowired
    CompanyServiceTest(CompanyService service) {
        super(service, new CompanyTestData(), CompanyTestData.COMPANY_MATCHER);
    }
}
