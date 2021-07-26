package ru.project.drivingschool.service;

import org.springframework.stereotype.Service;
import ru.project.drivingschool.model.Contract;
import ru.project.drivingschool.repository.ContractRepository;
import ru.project.drivingschool.to.ResultPage;

import static ru.project.drivingschool.model.Index.*;

@Service
public class ContractService extends AbstractService<Contract> {

    private ContractRepository repository;

    public ContractService(ContractRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public ResultPage<Contract> getUserContracts (int offset, int limit, Long userId) {
        return convertFromPage(this.repository.getUserContracts(offset, limit, userId));
    }

    public ResultPage<Contract> getUserContracts (Long userId) {
        return convertFromPage(this.repository.getUserContracts(DEF_PAGE, DEF_LIMIT, userId));
    }

    @Override
    Contract getExample(boolean active) {
        return null;
    }
}
