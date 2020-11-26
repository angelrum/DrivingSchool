package ru.project.drivingschool.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.project.drivingschool.model.Company;
import ru.project.drivingschool.model.Employee;
import ru.project.drivingschool.repository.jpa.JpaCompanyRepository;
import ru.project.drivingschool.repository.jpa.JpaEmployeeRepository;
import ru.project.drivingschool.util.ValidationUtil;

import java.util.List;
import java.util.Objects;

@Repository
@Transactional(readOnly = true)
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

    @Transactional
    public Employee save(Employee e, long companyId, long createdBy) {
        Company company = companyId > ValidationUtil.REGISTER_SEQ ? companyRepository.getOne(companyId) : null;
        e.setCompany(company);
        return super.save(e, createdBy);
    }

    public Employee save(Employee e) {
        return save(e, null);
    }

    @Transactional
    public boolean delete(long companyId, long id) {
        return repository.delete(companyId, id)!=0;
    }

    public Employee getWithSchools(long id) {
        return repository.getWithSchool(id);
    }

    public Employee getByPhone(String phone) {
        return StringUtils.hasText(phone) ? repository.getFirstByPhone(phone) : null;
    }

}
