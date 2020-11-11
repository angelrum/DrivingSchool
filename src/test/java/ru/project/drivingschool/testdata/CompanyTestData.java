package ru.project.drivingschool.testdata;

import ru.project.drivingschool.TestMatcher;
import ru.project.drivingschool.model.Company;
import ru.project.drivingschool.model.Country;
import ru.project.drivingschool.model.User;

import java.util.List;

public class CompanyTestData {

    public static final TestMatcher<Company> COMPANY_MATCHER = TestMatcher.usingFieldsComparator(Company.class, "createdOn");

    public static final long COMPANY_ID1 = 10000;

    public static final long COMPANY_ID2 = 10001;
    // ('ООО "Рога и копыта"', 'Москва', 'ул.Ленина', 'д.1', '111100', '+7(911)111-11-11'),
    // ('ООО "Копыта и рога"', 'Москва', 'ул.Ленина', 'д.2', '111100', '+7(911)111-11-12');

    public static final long NOT_FOUND_ID = 99999;

    public static Company COMPANY1 = new Company(COMPANY_ID1, "ООО \"Рога и копыта\"", Country.RUS, "Москва", "ул.Ленина", "д.1", "111100", "+7(911)111-11-11", null, null);

    public static Company COMPANY2 = new Company(COMPANY_ID2, "ООО \"Копыта и рога\"", Country.RUS, "Москва", "ул.Ленина", "д.2", "111100", "+7(911)111-11-12", null, null);

    public static List<Company> COMPANYS = List.of(COMPANY1, COMPANY2);

    public static Company getNew() {
        Company companyNew = new Company(COMPANY1);
        companyNew.setId(null);
        companyNew.setName("Новая компания");
        return companyNew;
    }

    public static User getUpdate() {
        Company upd = new Company(COMPANY2);
        upd.setName("Обновленная компания");
        return upd;
    }

}
