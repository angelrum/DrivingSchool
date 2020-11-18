package ru.project.drivingschool.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.project.drivingschool.model.Employee;
import ru.project.drivingschool.repository.EmployeeRepository;

import javax.validation.constraints.Min;
import java.util.List;

import static ru.project.drivingschool.util.ValidationUtil.*;

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

    public Employee get(long companyId, long id) {
        return checkNotFoundWithId(repository.get(companyId, id), companyId, id);
    }

    public Employee getByPhone(String phone) {
        checkNotNull(phone);
        return checkNotFound(repository.getByPhone(phone), String.format("Not fount with phone=%s", phone));
    }

    public Employee create(Employee employee, long companyId, long createdBy) {
        log.info("Create employee {}. Company={} and created={}", employee.toString(), companyId, createdBy);
        checkNotNull(employee);
        checkNew(employee);
        return repository.save(employee, companyId, createdBy);
    }

    public Employee update(Employee employee, long companyId, long createdBy) {
        log.info("Update employee {}. Company={} and changed={}", employee.toString(), companyId, createdBy);
        checkNotNull(employee);
        return checkNotFoundWithId(repository.save(employee, companyId, createdBy), companyId, employee.id());
    }

    public void delete(long companyId, long id) {
        log.info("Delete employee. Company={}, id={}", companyId, id);
        if (!repository.delete(companyId, id))
            checkNotFoundWithId(null, companyId, id);
    }

    public Employee getWithSchools(long id) {
        checkByRegisterId(id);
        return checkNotFoundWithId(repository.getWithSchools(id), id);
    }
}
