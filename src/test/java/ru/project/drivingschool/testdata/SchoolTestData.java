package ru.project.drivingschool.testdata;

import ru.project.drivingschool.model.School;

import java.util.List;

public class SchoolTestData {

    public static final long SCHOOL_ID1 = 10_002;
    public static final long SCHOOL_ID2 = 10_003;

    //(10000, 'Школа N1', 'Москва', 'ул.Первого мая', 'д.160, 5 этаж', '111100', '8(495)111-111-11', null, 1000),
    //(10000, 'Школа N2', 'Москва', 'ул.Нижняя Красносельская', 'д.40, 5 этаж', '111100', '8(495)111-111-12', null, 1000);

    public static School SCHOOL1 = new School(SCHOOL_ID1, CompanyTestData.COMPANY1, "Школа N1", "Москва", "ул.Первого мая", "д.160, 5 этаж", "111100", "8(495)111-111-11", null, true, null, null, null, null);
    public static School SCHOOL2 = new School(SCHOOL_ID2, CompanyTestData.COMPANY1, "Школа N2", "Москва", "ул.Нижняя Красносельская", "д.40, 5 этаж", "111100", "8(495)111-111-12", null, true, null, null, null, null);

    public static List<School> SCHOOLS = List.of(SCHOOL2, SCHOOL1);
}
