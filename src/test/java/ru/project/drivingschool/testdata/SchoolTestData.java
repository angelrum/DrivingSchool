package ru.project.drivingschool.testdata;

import ru.project.drivingschool.TestMatcher;
import ru.project.drivingschool.model.Address;
import ru.project.drivingschool.model.School;
import ru.project.drivingschool.model.directory.Country;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class SchoolTestData implements TestDataInterface<School>  {

    public static final TestMatcher<School> SCHOOL_MATCHER = TestMatcher.usingFieldsComparator(School.class, "schoolUsers", "company", "history");

    private CompanyTestData companyData = new CompanyTestData();

    private final long schoolId1 = 10_008;
    private final long schoolId2 = 10_009;
    private final long schoolId3 = 10_010;

    private Address address1 =  new Address(10_003L, Country.RUS, "Москва", "Москва", "123458", "ул. Нижняя Красносельская", null, "д.15", 3, 343, null, null);
    private Address address2 =  new Address(10_004L, Country.RUS, "Москва", "Москва", "123459", "ул. Антонова", "корп.8", "д.1", 2, 283, null, null);
    private Address address3 =  new Address(10_005L, Country.RUS, "Москва", "Москва", "123459", "ул. Миронова", null, "д.3", 2, 15, null, null);

    private School school1 = new School(schoolId1, companyData.getObjectById(companyData.getId1()), "Автошкола N1 \"На Первого мая\"", "Автошкола N1", "8(495)111-111-11", "avtonumber1@mail.ru", address1, true, null, null);
    private School school2 = new School(schoolId2, companyData.getObjectById(companyData.getId1()), "Автошкола N2 \"На Антонова\"", "Автошкола N2", "8(495)211-111-11", "avtonumber2@mail.ru", address2, true, null, null);
    private School school3 = new School(schoolId3, companyData.getObjectById(companyData.getId2()), "Автошкола N1 \"На Миронова\"", "Автошкола N1", "8(495)311-111-11", "avto_mironova@mail.ru", address3, true, null, null);

    @Override
    public long getId1() {
        return schoolId1;
    }

    @Override
    public long getId2() {
        return schoolId2;
    }

    @Override
    public Collection<School> getAll() {
        return new ArrayList<>(List.of(school1, school2, school3));
    }

    public Collection<School> getAllByCompany(long companyId) {
        return getAll().stream().filter(s->s.getCompany().id()==companyId).collect(Collectors.toList());
    }

    @Override
    public School getNew() {
        School create = new School(school1);
        create.setId(null);
        create.setName("Школа N3");
        create.setPhone("8(495)222-111-11");
        create.setEmail("test@test.ru");
        return create;
    }

    @Override
    public School getUpdate() {
        School upd = new School(school1);
        upd.setName("Обновленная школа");
        upd.setPhone("8(495)211-111-11");
        return upd;
    }

}
