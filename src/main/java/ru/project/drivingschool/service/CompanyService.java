package ru.project.drivingschool.service;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import ru.project.drivingschool.model.Company;
import ru.project.drivingschool.model.School;
import ru.project.drivingschool.model.User;
import ru.project.drivingschool.model.directory.Role;
import ru.project.drivingschool.model.directory.Status;
import ru.project.drivingschool.repository.CompanyRepository;
import ru.project.drivingschool.to.ResultPage;

import java.util.Objects;

import static ru.project.drivingschool.model.Index.*;

@Service
public class CompanyService extends AbstractService<Company> {

    private CompanyRepository repository;

    public CompanyService(CompanyRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public ResultPage<Company> getAll(Boolean active) {
        if (Objects.nonNull(active))
            return super.getAll(Example.of(getExample(active)));
        else
            return super.getAll();
    }

    public ResultPage<Company> getAll(int offset, int limit, Boolean active) {
        if (Objects.nonNull(active))
            return super.getAll(offset, limit, Example.of(getExample(active)));
        else
            return super.getAll(offset, limit);
    }

    public ResultPage<School> getSchools(Long companyId, Boolean active) {
        return this.getSchools(companyId, DEF_PAGE, DEF_LIMIT, active);
    }

    public ResultPage<School> getSchools(Long companyId, int offset, int limit, Boolean active) {
        return convertFromPage(repository.getWithSchools(companyId, offset, limit, active));
    }

    public ResultPage<User> getUsers(Long companyId) {
        return this.getUsers(companyId, DEF_PAGE, DEF_LIMIT, null, null, null);
    }

    public ResultPage<User> getUsers(Long companyId, Boolean active, Status status, Role role) {
        return this.getUsers(companyId, DEF_PAGE, DEF_LIMIT, active, status, role);
    }

    public ResultPage<User> getUsers(Long companyId, int offset, int limit, Boolean active, Status status, Role role) {
        return convertFromPage(repository.getWithUsers(companyId, offset, limit, active, status, role));
    }

    @Override
    protected Company getExample(boolean active) {
        Company company = new Company();
        company.setActive(active);
        company.setHistory(null);
        return company;
    }
}
