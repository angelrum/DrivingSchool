package ru.project.drivingschool.testdata;

import ru.project.drivingschool.TestMatcher;
import ru.project.drivingschool.model.Address;
import ru.project.drivingschool.model.Company;
import ru.project.drivingschool.model.directory.Country;

import java.util.ArrayList;
import java.util.List;

public class CompanyTestData implements TestDataInterface<Company> {

    public static final TestMatcher<Company> COMPANY_MATCHER = TestMatcher.usingFieldsComparator(Company.class, "schools", "history");

    private final long companyId1 = 10006;
    private final long companyId2 = 10007;

    private Address address1 = new Address(10_000L, Country.RUS, "Москва", "Москва", "123456", "ул. Первого Мая", "стр.1", "д.12", 1, 105, null, null);
    private Address address2 = new Address(10_001L, Country.RUS, "Москва", "Москва", "123456", "ул. Первого Мая", "стр.1", "д.15", 1, 117, null, null);
    private Address address3 = new Address(10_002L, Country.RUS, "Москва", "Москва", "123457", "ул. Ленина", null, "д.12", 3, 305, null, null);


    private Company company1 = new Company(companyId1, "ООО \"Рога и копыта\"", "Рога и копыта", "+7(911)311-11-11", "roga@mail.ru", "roga.ru", true, address1, address2, null, null);
    private Company company2 = new Company(companyId2, "ООО \"Новые рога\"", "Новые рога", "+7(911)311-11-12", "roga_new@mail.ru", "newroga.ru", true, address3, address3, null, null);

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
