package ru.project.drivingschool.util;

import ru.project.drivingschool.model.Employee;
import ru.project.drivingschool.to.EmployeeTo;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeUtil {

    public static EmployeeTo createTo(Employee e) {
        return new EmployeeTo(e.getId(), e.getPhone(), e.getPassword(), e.getAvatar(), e.getFirstname(), e.getLastname(), e.getMiddlename(), e.getEmail(), e.isEnabled(), e.getScore(), e.getRoles());
    }

    public static List<EmployeeTo> createTos(List<Employee> employees) {
        return employees.stream()
                .map(EmployeeUtil::createTo)
                .collect(Collectors.toList());
    }

}
