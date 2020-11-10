package ru.project.drivingschool.service;

import org.springframework.stereotype.Service;
import ru.project.drivingschool.model.Employee;
import ru.project.drivingschool.repository.EmployeeRepository;

@Service
public class EmployeeService extends AbstractService<Employee> {

    public EmployeeService(EmployeeRepository repository) {
        super(repository);
    }
}
