package ru.project.drivingschool.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import ru.project.drivingschool.model.Company;
import ru.project.drivingschool.model.School;
import ru.project.drivingschool.model.User;
import ru.project.drivingschool.model.embedded.SchoolUserId;
import ru.project.drivingschool.model.embedded.SchoolUsers;
import ru.project.drivingschool.repository.jpa.JpaCompanyRepository;
import ru.project.drivingschool.repository.jpa.JpaSchoolRepository;
import ru.project.drivingschool.repository.jpa.JpaSchoolUsersRepository;
import ru.project.drivingschool.repository.jpa.JpaUserRepository;
import ru.project.drivingschool.util.ValidationUtil;

import java.util.List;
import java.util.Set;

@Repository
@Transactional(readOnly = true)
public class SchoolRepository extends AbstractKeyHistoryRepository<School> {
    
    private JpaSchoolRepository repository;

    private JpaCompanyRepository companyRepository;

    private JpaSchoolUsersRepository schoolUsersRepository;

    public SchoolRepository(JpaSchoolRepository repository,
                            JpaCompanyRepository companyRepository,
                            JpaUserRepository userRepository,
                            JpaSchoolUsersRepository schoolUsersRepository) {
        super(repository, userRepository);
        this.repository = repository;
        this.companyRepository = companyRepository;
        this.schoolUsersRepository = schoolUsersRepository;
    }

    public List<School> getAll(long companyId) {
        return repository.getAll(companyId);
    }
    
    public School getWithUsers(long id) {
        return repository.getWithUsers(id);
    }

    public School save(School e, long companyId, long userId) {
        Company company = companyRepository.getOne(companyId);
        e.setCompany(company);
        if (!e.isNew() && CollectionUtils.isEmpty(e.getSchoolUsers())) {
            Set<SchoolUsers> users = schoolUsersRepository.getBySchool(e.id());
            e.setUsers(users);
        }

        return super.save(e, userId);
    }

}
