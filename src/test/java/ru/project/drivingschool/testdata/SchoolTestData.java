package ru.project.drivingschool.testdata;

import ru.project.drivingschool.TestMatcher;
import ru.project.drivingschool.model.School;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SchoolTestData implements TestDataInterface<School>  {

    public static final TestMatcher<School> SCHOOL_MATCHER = TestMatcher.usingFieldsComparator(School.class, "employees", "users", "company", "createdOn", "changedOn", "createdBy");

    private final long schoolId1 = 10_002;
    private final long schoolId2 = 10_003;

    private CompanyTestData companyData = new CompanyTestData();

    //(10000, 'Школа N1', 'Москва', 'ул.Первого мая', 'д.160, 5 этаж', '111100', '8(495)111-111-11', null, 1000),
    //(10000, 'Школа N2', 'Москва', 'ул.Нижняя Красносельская', 'д.40, 5 этаж', '111100', '8(495)111-111-12', null, 1000);

    private School school1 = new School(schoolId1, companyData.getObjectById(companyData.getId1()), "Школа N1", "Москва", "ул.Первого мая", "д.160, 5 этаж", "111100", "8(495)111-111-11", null, true, null, null, null, null, null, null);

    private School school2 = new School(schoolId2, companyData.getObjectById(companyData.getId1()), "Школа N2", "Москва", "ул.Нижняя Красносельская", "д.40, 5 этаж", "111100", "8(495)111-111-12", null, true, null, null, null, null, null, null);

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
        return new ArrayList<>(List.of(school1, school2));
    }

    @Override
    public School getNew() {
        School create = new School(school1);
        create.setId(null);
        create.setName("Школа N3");
        create.setHome("д.220");
        create.setEmail("test@test.ru");
        return create;
    }

    @Override
    public School getUpdate() {
        School upd = new School(school1);
        upd.setHome("д.30");
        upd.setPhone("8(495)211-111-11");
        return upd;
    }

}
