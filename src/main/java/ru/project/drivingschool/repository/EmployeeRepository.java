package ru.project.drivingschool.repository;

import org.springframework.stereotype.Repository;
import ru.project.drivingschool.model.Employee;
import ru.project.drivingschool.repository.jpa.JpaEmployeeRepository;

import java.util.List;

@Repository
public class EmployeeRepository extends AbstractNamedHistoryRepository<Employee> {

    private JpaEmployeeRepository repository;

    public EmployeeRepository(JpaEmployeeRepository employeeRepository) {
        super(employeeRepository, employeeRepository);
        this.repository = employeeRepository;
    }

    public List<Employee> getAll(long companyId) {
        return this.repository.getAllByCompanyIdOrderById(companyId);
    }
}
