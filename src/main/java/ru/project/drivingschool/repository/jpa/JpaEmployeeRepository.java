package ru.project.drivingschool.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.project.drivingschool.model.Employee;

public interface JpaEmployeeRepository extends JpaNamedRepository<Employee> {
}
