package ru.project.drivingschool.service;

import org.springframework.stereotype.Service;
import ru.project.drivingschool.model.Employee;
import ru.project.drivingschool.repository.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeService extends AbstractService<Employee> {

    private EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public List<Employee> getAll(long companyId) {
        return this.repository.getAll(companyId);
    }

}
