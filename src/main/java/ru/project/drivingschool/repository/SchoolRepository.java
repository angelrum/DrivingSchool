package ru.project.drivingschool.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import ru.project.drivingschool.model.School;
import ru.project.drivingschool.model.link.SchoolUsers;
import ru.project.drivingschool.repository.jpa.JpaAddressRepository;
import ru.project.drivingschool.repository.jpa.JpaSchoolRepository;
import ru.project.drivingschool.repository.jpa.JpaSchoolUsersRepository;
import ru.project.drivingschool.repository.jpa.JpaUserRepository;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Repository
@Transactional(readOnly = true)
public class SchoolRepository extends AbstractKeyHistoryRepository<School> {
    
    private JpaSchoolRepository repository;

    private JpaSchoolUsersRepository schoolUsersRepository;

    public SchoolRepository(JpaSchoolRepository repository, JpaUserRepository userRepository,
                            JpaSchoolUsersRepository schoolUsersRepository, JpaAddressRepository addressRepository) {
        super(repository, userRepository, addressRepository);
        this.repository = repository;
        this.schoolUsersRepository = schoolUsersRepository;
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
            s.setSchoolUsers(users);
        }
//        if (!s.isNew()) {
//            School old = repository.getOne(s.id());
//            if (Objects.isNull(s.getAddress()))
//                s.setAddress(old.getAddress());
//            if (Objects.isNull(s.getHistory()))
//                s.setHistory(old.getHistory());
//        }
        return super.save(s, userId);
    }

}
