package ru.project.drivingschool.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import ru.project.drivingschool.model.User;
import ru.project.drivingschool.model.embedded.SchoolUsers;
import ru.project.drivingschool.repository.jpa.JpaSchoolUsersRepository;
import ru.project.drivingschool.repository.jpa.JpaUserRepository;

import java.util.List;
import java.util.Set;

@Repository
@Transactional(readOnly = true)
public class UserRepository extends AbstractKeyHistoryRepository<User> {

    private JpaUserRepository repository;

    private JpaSchoolUsersRepository schoolUsersRepository;

    public UserRepository(JpaUserRepository repository, JpaSchoolUsersRepository schoolUsersRepository) {
        super(repository, repository);
        this.repository = repository;
        this.schoolUsersRepository = schoolUsersRepository;
    }

    @Transactional
    public User save(User u, long userId) {
        if (!u.isNew() && CollectionUtils.isEmpty(u.getSchoolUsers())) {
            Set<SchoolUsers> schoolUsers = schoolUsersRepository.getByUser(u.id());
            u.setSchoolUsers(schoolUsers);
        }

        return super.save(u, userId);
    }

    public User getByPhone(String phone){
        return repository.getByPhone(phone);
    }

    public List<User> getAllActive() {
        return repository.findAllByActiveTrueOrderById();
    }

}
