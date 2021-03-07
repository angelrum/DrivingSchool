package ru.project.drivingschool.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.project.drivingschool.AuthorizedUserTest;
import ru.project.drivingschool.model.Address;
import ru.project.drivingschool.model.Company;
import ru.project.drivingschool.model.School;
import ru.project.drivingschool.testdata.CompanyTestData;
import ru.project.drivingschool.testdata.SchoolTestData;
import ru.project.drivingschool.testdata.UserTestData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static ru.project.drivingschool.testdata.SchoolTestData.*;

@SpringBootTest
class CompanyServiceTest extends AbstractServiceTest<Company> {

    private CompanyService service;

    private CompanyTestData testData;

    private UserTestData userData;

    private SchoolTestData schoolTestData = new SchoolTestData();

    @Autowired
    CompanyServiceTest(CompanyService service) {
        super(service, new CompanyTestData(), CompanyTestData.COMPANY_MATCHER);
        this.service = service;
        this.testData = new CompanyTestData();
        this.userData = new UserTestData();
    }

    @Test
    void updateAddress() {
        Company company = testData.getObjectById(testData.getId1());
        Address address = company.getAddressActual();
        address.setStreet("Новая улица");
        service.update(company, userData.getId1());
        Company upd = service.get(company.id());
        CompanyTestData.COMPANY_MATCHER.assertMatch(company, upd);
    }

//    @Test
//    void getSchools() {
//        Company company = service.getWithSchools(testData.getId1());
//        List<School> schools = new ArrayList<>(company.getSchools());
//        schools.sort(Comparator.comparing(School::id));
//        SCHOOL_MATCHER.assertMatch(schools, schoolTestData.getAllByCompany(testData.getId1()));
//    }
//
//    @Test
//    void checkSchoolsAfterUpdate() {
//        update();
//        getSchools();
//    }
//
//    @Test
//    void updateSchoolsFromCompany() {
//        Company company = service.getWithSchools(testData.getId1());
//        School upd = schoolTestData.getUpdate();
//        company.getSchools().removeIf(s->s.id()==upd.id());
//        company.getSchools().add(upd);
//        service.update(company, AuthorizedUserTest.getId());
//        getSchools();
//    }

}
