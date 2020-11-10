package ru.project.drivingschool.repository;

import org.springframework.stereotype.Repository;
import ru.project.drivingschool.model.Employee;
import ru.project.drivingschool.repository.jpa.JpaEmployeeRepository;

@Repository
public class EmployeeRepository extends AbstractNamedHistoryRepository<Employee> {

    public EmployeeRepository(JpaEmployeeRepository employeeRepository) {
        super(employeeRepository, employeeRepository);
    }
}
