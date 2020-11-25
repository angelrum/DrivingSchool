package ru.project.drivingschool.testdata;

import ru.project.drivingschool.TestMatcher;
import ru.project.drivingschool.model.Company;
import ru.project.drivingschool.model.Country;
import ru.project.drivingschool.model.User;

import java.util.ArrayList;
import java.util.List;

public class CompanyTestData implements TestDataInterface<Company> {

    public static final TestMatcher<Company> COMPANY_MATCHER = TestMatcher.usingFieldsComparator(Company.class, "createdOn", "schools");

    private final long companyId1 = 10000;

    private final long companyId2 = 10001;
    // ('ООО "Рога и копыта"', 'Москва', 'ул.Ленина', 'д.1', '111100', '+7(911)111-11-11'),
    // ('ООО "Копыта и рога"', 'Москва', 'ул.Ленина', 'д.2', '111100', '+7(911)111-11-12');

    private Company company1 = new Company(companyId1, "ООО \"Рога и копыта\"", Country.RUS, "Москва", "ул.Ленина", "д.1", "111100", "+7(911)111-11-11", null, null, null);

    private Company company2 = new Company(companyId2, "ООО \"Копыта и рога\"", Country.RUS, "Москва", "ул.Ленина", "д.2", "111100", "+7(911)111-11-12", null, null, null);

    @Override
    public Company getNew() {
        Company companyNew = new Company(company1);
        companyNew.setId(null);
        companyNew.setName("Новая компания");
        return companyNew;
    }

    @Override
    public Company getUpdate() {
        Company upd = new Company(company2);
        upd.setName("Обновленная компания");
        return upd;
    }

    @Override
    public long getId1() {
        return companyId1;
    }

    @Override
    public long getId2() {
        return companyId2;
    }

    @Override
    public List<Company> getAll() {
        return new ArrayList<>(List.of(company1, company2));
    }
}
