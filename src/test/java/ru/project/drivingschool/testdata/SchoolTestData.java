package ru.project.drivingschool.testdata;

import ru.project.drivingschool.TestMatcher;
import ru.project.drivingschool.model.Address;
import ru.project.drivingschool.model.Company;
import ru.project.drivingschool.model.School;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import static ru.project.drivingschool.testdata.AddressTestData.*;
import static ru.project.drivingschool.testdata.CompanyTestData.*;

public class SchoolTestData implements TestDataInterface<School>  {

    public static final TestMatcher<School> SCHOOL_MATCHER = TestMatcher.usingFieldsComparator(School.class, "schoolUsers", "company", "history");

    private final CompanyTestData companyData = new CompanyTestData();

    public static final long SCHOOL_ID_1 = 1_004L;
    public static final long SCHOOL_ID_2 = 1_005L;
    public static final long SCHOOL_ID_3 = 1_006L;

    public School SCHOOL_1 = new School(SCHOOL_ID_1, companyData.getObjectById(COMPANY_ID_1), "Автошкола N1 \"На Первого мая\"", "Автошкола N1", "8(495)111-111-11", "avtonumber1@mail.ru", address1, true, null, null);
    public School SCHOOL_2 = new School(SCHOOL_ID_2, companyData.getObjectById(COMPANY_ID_2), "Автошкола N2 \"На Антонова\"", "Автошкола N2", "8(495)211-111-11", "avtonumber2@mail.ru", address4, true, null, null);
    public School SCHOOL_3 = new School(SCHOOL_ID_3, companyData.getObjectById(COMPANY_ID_2), "Автошкола N1 \"На Миронова\"", "Автошкола N1", "8(495)311-111-11", "avto_mironova@mail.ru", address5, true, null, null);

    @Override
    public long getId1() {
        return SCHOOL_ID_1;
    }

    @Override
    public long getId2() {
        return SCHOOL_ID_2;
    }

    @Override
    public Collection<School> getAll() {
        return new ArrayList<>(List.of(SCHOOL_1, SCHOOL_2, SCHOOL_3));
    }

    public Collection<School> getAllByCompany(long companyId) {
        return getAll().stream().filter(s->s.getCompany().id()==companyId).collect(Collectors.toList());
    }

    @Override
    public School getNew() {
        School create = new School(SCHOOL_1);
        create.setId(null);
        create.setName("Школа N3");
        create.setPhone("8(495)222-111-11");
        create.setEmail("test@test.ru");
        Address a = new Address(create.getAddress());
        a.setId(null);
        create.setAddress(a);
        return create;
    }

    @Override
    public School getUpdate() {
        School upd = new School(SCHOOL_1);
        upd.setName("Обновленная школа");
        upd.setPhone("8(495)211-111-11");
        Address address = upd.getAddress();
        address.setStreet("Новая улица");
        return upd;
    }

}
