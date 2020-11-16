package ru.project.drivingschool.repository.jpa;

import ru.project.drivingschool.model.Employee;

import java.util.List;

public interface JpaEmployeeRepository extends JpaNamedRepository<Employee> {

    List<Employee> getAllByCompanyIdOrderById(long companyId);

}
