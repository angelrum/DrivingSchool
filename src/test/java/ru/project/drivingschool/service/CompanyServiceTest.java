package ru.project.drivingschool.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.project.drivingschool.model.Address;
import ru.project.drivingschool.model.Company;
import ru.project.drivingschool.model.School;
import ru.project.drivingschool.model.User;
import ru.project.drivingschool.testdata.CompanyTestData;
import ru.project.drivingschool.testdata.SchoolTestData;
import ru.project.drivingschool.testdata.UserTestData;
import ru.project.drivingschool.to.ResultPage;
import java.util.List;

import static ru.project.drivingschool.testdata.SchoolTestData.*;
import static ru.project.drivingschool.testdata.CompanyTestData.*;

@SpringBootTest
class CompanyServiceTest extends AbstractServiceTest<Company> {

    private CompanyService service;

    private CompanyTestData testData;

    private UserTestData userData;

    private SchoolTestData schoolTestData = new SchoolTestData();

    @Autowired
    CompanyServiceTest(CompanyService service) {
        super(service, new CompanyTestData(), COMPANY_MATCHER);
        this.service = service;
        this.testData = new CompanyTestData();
        this.userData = new UserTestData();
    }

    @Test
    void updateAddress() {
        Company company = testData.getObjectById(testData.getId1());
        Address ad1 = company.getAddressLegal();
        ad1.setStreet("Новая улица");
        ad1.setCity("Алабама");
        service.update(company, null); //userData.getId1()
        Company upd = service.get(company.id());
        COMPANY_MATCHER.assertMatch(company, upd);
    }

    @Test
    void getSchools() {
        ResultPage<School> schools = service.getSchools(testData.getId2(), true);
        List<School> listSchool = schools.getResult();
        SCHOOL_MATCHER.assertMatch(listSchool, List.of(schoolTestData.getObjectById(SCHOOL_ID_2), schoolTestData.getObjectById(SCHOOL_ID_3)));
    }

    @Test
    void getAllCompanies() {
        List<Company> companies = service.getAll().getResult();
        COMPANY_MATCHER.assertMatch(companies, testData.getAll());
    }

    @Test
    void getUsers() {
        ResultPage<User> users = service.getUsers(testData.getId1());
        users.getResult().forEach(System.out::println);
    }
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
