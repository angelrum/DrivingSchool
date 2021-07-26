package ru.project.drivingschool.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import ru.project.drivingschool.model.School;
import ru.project.drivingschool.model.User;
import ru.project.drivingschool.model.directory.Role;
import ru.project.drivingschool.model.directory.Status;
import ru.project.drivingschool.model.embedded.History;
import ru.project.drivingschool.model.embedded.SchoolUserId;
import ru.project.drivingschool.model.link.SchoolUsers;
import ru.project.drivingschool.model.link.UserRoles;
import ru.project.drivingschool.repository.jpa.JpaAddressRepository;
import ru.project.drivingschool.repository.jpa.JpaSchoolRepository;
import ru.project.drivingschool.repository.jpa.JpaSchoolUsersRepository;
import ru.project.drivingschool.repository.jpa.JpaUserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Repository
@Transactional(readOnly = true)
public class UserRepository extends AbstractKeyHistoryRepository<User> {

    private JpaUserRepository repository;

    private JpaSchoolRepository schoolRepository;

    public UserRepository(JpaUserRepository repository, JpaSchoolRepository schoolRepository, JpaAddressRepository addressRepository) {
        super(repository, repository, addressRepository);
        this.repository = repository;
        this.schoolRepository = schoolRepository;
    }

    @Transactional
    public User save(User u, long userId) {
        if (u.isNew() && !CollectionUtils.isEmpty(u.getRoles())) {
            Set<UserRoles> userRoles = u.getRoles();
            u.setRoles(new HashSet<>());
            super.save(u, userId);
            u.setRoles(fillDataUserRoles(userRoles, u));
            u.setSchoolUsers(fillDataSchoolUsers(userRoles, u, repository.getOne(userId)));
            return this.repository.save(u);
        }
        return super.save(u, userId);
    }

    public User getByPhone(String phone){
        return repository.getByPhone(phone);
    }

    private Set<UserRoles> fillDataUserRoles(Set<UserRoles> userRoles, User u) {
        Set<UserRoles> userRolesSet = new HashSet<>();
        userRoles.forEach(ur -> {
            School school = this.schoolRepository
                    .findById(ur.getId().getSchoolId()).orElse(null);
            if (Objects.nonNull(school)){
                ur.getId().setUserId(u.id());
                ur.setSchool(school);
                ur.setUser(u);
                userRolesSet.add(ur);
            }
        });
        return userRolesSet;
    }

    private Set<SchoolUsers> fillDataSchoolUsers(Set<UserRoles> userRoles, User u, User createdBy) {
        Set<SchoolUsers> schoolUsers = new HashSet<>();
        userRoles.forEach(ur -> {
            Role role = ur.getRole();
            Status status = role.equals(Role.ADMIN) || role.equals(Role.MANAGER) ? Status.CONTRACTED : Status.PENDING;
            SchoolUsers su = new SchoolUsers(ur.getSchool(), u, status, new History(createdBy));
            schoolUsers.add(su);
        });
        return schoolUsers;
    }

}
