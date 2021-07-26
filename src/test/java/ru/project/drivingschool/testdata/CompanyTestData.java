package ru.project.drivingschool.testdata;

import ru.project.drivingschool.TestMatcher;
import ru.project.drivingschool.model.Company;
import ru.project.drivingschool.model.embedded.History;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static ru.project.drivingschool.testdata.AddressTestData.*;

public class CompanyTestData implements TestDataInterface<Company> {

    public static final TestMatcher<Company> COMPANY_MATCHER = TestMatcher.usingFieldsComparator(Company.class, "schools", "companyUsers", "history");

    public static final long COMPANY_ID_1 = 1_002L;
    public static final long COMPANY_ID_2 = 1_003L;

    public Company COMPANY_1 = new Company(COMPANY_ID_1, "ООО \"Рога и копыта\"", "Рога и копыта", "+7(911)311-11-11", "roga@mail.ru", "roga.ru", true, address1, address1, null, null, new History());
    public Company COMPANY_2 = new Company(COMPANY_ID_2, "ООО \"Новые рога\"", "Новые рога", "+7(911)311-11-12", "roga_new@mail.ru", "newroga.ru", true, address2, address3, null, null, new History());

    @Override
    public Company getNew() {
        Company companyNew = new Company(COMPANY_1);
        companyNew.setId(null);
        companyNew.setName("Новая компания");
        companyNew.setAddressLegal(null);
        companyNew.setAddressActual(null);
//        Address ad = companyNew.getAddressLegal();
//        ad.setCity("Новый город");
//        ad.setId(null);
//        companyNew.setAddressLegal(new Address(ad));
//        companyNew.setAddressLegal(new Address(ad));
        return companyNew;
    }

    @Override
    public Company getUpdate() {
        Company upd = new Company(COMPANY_2);
        upd.setName("Обновленная компания");
        return upd;
    }

    @Override
    public long getId1() {
        return COMPANY_ID_1;
    }

    @Override
    public long getId2() {
        return COMPANY_ID_2;
    }

    @Override
    public List<Company> getAll() {
        return new ArrayList<>(List.of(COMPANY_1, COMPANY_2));
    }
}
