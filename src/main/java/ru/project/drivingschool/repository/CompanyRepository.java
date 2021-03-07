package ru.project.drivingschool.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.project.drivingschool.model.Address;
import ru.project.drivingschool.model.Company;
import ru.project.drivingschool.repository.jpa.JpaCompanyRepository;
import ru.project.drivingschool.repository.jpa.JpaUserRepository;

import java.util.Objects;

@Repository
@Transactional(readOnly = true)
public class CompanyRepository extends AbstractKeyHistoryRepository<Company> {

    private JpaCompanyRepository repository;

    public CompanyRepository(JpaCompanyRepository repository, JpaUserRepository userRepository) {
        super(repository, userRepository);
        this.repository = repository;
    }

    public Company getWithSchools(Long id) {
        return repository.getWithSchools(id);
    }

    @Override
    @Transactional
    public Company save(Company company, Long userId) {
        Address al = company.getAddressLegal();
        Address ac = company.getAddressActual();

        if (!company.isNew() && (Objects.isNull(al) || Objects.isNull(ac))) {
            Company old = repository.getOne(company.id());
            if (Objects.isNull(al))
                company.setAddressLegal(old.getAddressLegal());
            if (Objects.isNull(ac))
                company.setAddressActual(old.getAddressActual());
        } else if (Objects.nonNull(al) //адреса равны
                && Objects.nonNull(ac)
                && !al.isNew() && !ac.isNew()
                && ac.getId().compareTo(al.getId()) == 0) {
            company.setAddressActual(al);
        }

        return super.save(company, userId);
    }
}
