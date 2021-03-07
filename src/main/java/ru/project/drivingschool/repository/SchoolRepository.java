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
import java.util.Objects;
import java.util.Set;

@Repository
@Transactional(readOnly = true)
public class SchoolRepository extends AbstractKeyHistoryRepository<School> {
    
    private JpaSchoolRepository repository;

    private JpaSchoolUsersRepository schoolUsersRepository;

    public SchoolRepository(JpaSchoolRepository repository,
                            JpaUserRepository userRepository,
                            JpaSchoolUsersRepository schoolUsersRepository) {
        super(repository, userRepository);
        this.repository = repository;
        this.schoolUsersRepository = schoolUsersRepository;
    }

    public List<School> getAll(long companyId) {
        return repository.getAll(companyId);
    }

    public List<School> getByCity(String city) {
        return repository.getByCity(city);
    }
    
    public School getWithUsers(long id) {
        return repository.getWithUsers(id);
    }

    @Override
    @Transactional
    public School save(School s, Long userId) {
        if (!s.isNew() && CollectionUtils.isEmpty(s.getSchoolUsers())) {
            Set<SchoolUsers> users = schoolUsersRepository.getBySchool(s.id());
            s.setUsers(users);
        }
        if (!s.isNew()) {
            School old = repository.getOne(s.id());
            if (Objects.isNull(s.getAddress()))
                s.setAddress(old.getAddress());
            if (Objects.isNull(s.getHistory()))
                s.setHistory(old.getHistory());
        }

        return super.save(s, userId);
    }

}
