package ru.project.drivingschool.testdata;

import ru.project.drivingschool.model.Address;
import ru.project.drivingschool.model.directory.Country;

public class AddressTestData {

    public static final Long ADDRESS_ID1 = 10_000L;
    public static final Long ADDRESS_ID2 = 10_001L;
    public static final Long ADDRESS_ID3 = 10_002L;
    public static final Long ADDRESS_ID4 = 10_003L;
    public static final Long ADDRESS_ID5 = 10_004L;
    public static final Long ADDRESS_ID6 = 10_005L;

    public static Address address1 = new Address(ADDRESS_ID1, Country.RUS, "123456", "Москва", "Москва", null, "ул. Первого Мая", "д.12", 1, "105", null, null, 5);
    public static Address address2 = new Address(ADDRESS_ID2, Country.RUS, "123456", "Москва", "Москва", null, "ул. Первого Мая", "д.15", 1, "117", null, null, 5);
    public static Address address3 = new Address(ADDRESS_ID3, Country.RUS, "123457", "Москва", "Москва", null, "ул. Ленина", "д.15", 3, "305", null, null, 5);
    public static Address address4 = new Address(ADDRESS_ID4, Country.RUS, "123458", "Москва", "Москва", null, "ул. Нижняя Красносельская", "д.15", 3, "343", null, null, 5);
    public static Address address5 = new Address(ADDRESS_ID5, Country.RUS, "123459", "Москва", "Москва", null, "ул. Антонова", "д.1", 2, "283", null, null, 5);
    public static Address address6 = new Address(ADDRESS_ID6, Country.RUS, "123459", "Москва", "Москва", null, "ул. Миронова", "д.3", 2, "15", null, null, 5);
}
