package ru.project.drivingschool.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import ru.project.drivingschool.model.Contract;
import ru.project.drivingschool.model.Index;
import ru.project.drivingschool.repository.jpa.JpaAddressRepository;
import ru.project.drivingschool.repository.jpa.JpaContractRepository;
import ru.project.drivingschool.repository.jpa.JpaUserRepository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
@Transactional(readOnly = true)
public class ContractRepository extends AbstractKeyHistoryRepository<Contract> {

    private JpaContractRepository repository;

    public ContractRepository(JpaContractRepository repository, JpaUserRepository userRepository, JpaAddressRepository addressRepository) {
        super(repository, userRepository, addressRepository);
        this.repository = repository;
    }

    public Page<Contract> getUserContracts(int page, int limit, Long userId) {
        return this.repository.getUserContracts(userId, PageRequest.of(page, limit));
    }

//    @Override
//    public Page<Contract> getAll(Pageable page) {
//        return this.repository.get(null, page);
//    }

    @Override
    public Contract get(Long id) {
        Contract contract = this.repository.get(id);
        if (Objects.nonNull(contract))
            contract.setChild(new HashSet<>(this.repository.getChild(id)));
        return contract;
    }

    @Override
    public Page<Contract> getAll(Pageable page) {
        Page<Contract> contractPage = super.getAll(page);
        List<Contract> list = contractPage.getContent();
        List<Contract> filter = list.stream()
                .filter(c -> Objects.nonNull(c.getParent()))
                .filter(list::contains).collect(Collectors.toList());
        for (Contract c : filter) {
            Contract parent = c.getParent();
            List<Contract> child = this.repository.getChild(parent.getId());
            if (!CollectionUtils.isEmpty(child)) {
                parent.setChild(new HashSet<>(child));
            }
        }
        return contractPage;
    }
}
