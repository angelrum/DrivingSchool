package ru.project.drivingschool.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.project.drivingschool.model.*;
import ru.project.drivingschool.model.directory.Role;
import ru.project.drivingschool.model.directory.Status;
import ru.project.drivingschool.repository.jpa.JpaAddressRepository;
import ru.project.drivingschool.repository.jpa.JpaCompanyRepository;
import ru.project.drivingschool.repository.jpa.JpaUserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
@Transactional(readOnly = true)
public class CompanyRepository extends AbstractKeyHistoryRepository<Company> {

    private JpaCompanyRepository repository;

    private JpaAddressRepository repAddress;

    public CompanyRepository(JpaCompanyRepository repository, JpaUserRepository userRepository, JpaAddressRepository repAddress) {
        super(repository, userRepository, repAddress);
        this.repository = repository;
        this.repAddress = repAddress;
    }

    public Page<School> getWithSchools(Long id, int page, int limit, Boolean active) {
        return repository.getSchools(id, active, PageRequest.of(page, limit));
    }

    public Page<User> getWithUsers(Long id, int page, int limit, Boolean active, Status status, Role role) {
        return repository.getUsers(id, active, status, role, PageRequest.of(page, limit));
    }

    @Override
    @Transactional
    public Company save(Company company, Long userId) {
        Address al = company.getAddressLegal();
        Address ac = company.getAddressActual();

        if (Objects.nonNull(al)) {
            Address ad = this.repAddress.save(al);
            company.setAddressActual(ad);
        }
        if (Objects.nonNull(ac)) {
            if (al.equals(ac))
                company.setAddressActual(company.getAddressLegal());
            else {
                Address ad = this.repAddress.save(ac);
                company.setAddressActual(ad);
            }
        }
        return super.save(company, userId);
    }

//    @Override
//    @Transactional
//    public boolean delete(Long id) {
//        Company company = super.get(id);
//        Address al = company.getAddressLegal();
//        Address ac = company.getAddressActual();
//
//        if (Objects.nonNull(al))
//            this.repAddress.delete(al.id());
//        if (Objects.nonNull(ac) && al.id()!=ac.id())
//            this.repAddress.delete(ac.id());
//
//        return super.delete(id);
//    }
}
