package ru.project.drivingschool.repository;

import org.springframework.stereotype.Repository;
import ru.project.drivingschool.model.Company;
import ru.project.drivingschool.model.Employee;
import ru.project.drivingschool.repository.jpa.JpaCompanyRepository;
import ru.project.drivingschool.repository.jpa.JpaEmployeeRepository;

import java.util.List;
import java.util.Objects;

@Repository
public class EmployeeRepository extends AbstractHistoryRepository<Employee> {

    private JpaEmployeeRepository repository;

    private JpaCompanyRepository companyRepository;

    public EmployeeRepository(JpaEmployeeRepository employeeRepository, JpaCompanyRepository companyRepository) {
        super(employeeRepository, employeeRepository);
        this.repository = employeeRepository;
        this.companyRepository = companyRepository;
    }

    public List<Employee> getAll(long companyId) {
        return this.repository.getAllByCompanyIdOrderById(companyId);
    }

    public Employee get(long companyId, long id) {
        return repository.findFirstByCompanyIdAndId(companyId, id);
    }

    public Employee save(Employee e, long companyId, long createdBy) {
        Company company = companyRepository.getOne(companyId);
        e.setCompany(company);
        return save(e, createdBy);
    }

    public boolean delete(long companyId, long id) {
        return repository.delete(companyId, id)!=0;
    }
}
