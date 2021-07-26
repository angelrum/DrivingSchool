package ru.project.drivingschool.controller.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.project.drivingschool.model.Contract;
import ru.project.drivingschool.service.ContractService;
import ru.project.drivingschool.to.ContractTo;
import ru.project.drivingschool.to.ResultPage;
import ru.project.drivingschool.util.Util;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

@RestController
@RequestMapping("/contracts")
public class ContractController extends AbstractController<Contract, ContractTo>{

    private ContractService service;

    public ContractController(ContractService service) {
        super(service);
        this.service = service;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResultPage<ContractTo> get(@RequestParam(required = false) Long id,
                                                    @RequestParam(required = false) Long userId,
                                                    @RequestParam(required = false) Integer limit,
                                                    @RequestParam(required = false) Integer offset) {
        if (Objects.isNull(userId) || Objects.nonNull(id))
            return super.get(id, null, limit, offset);
        else if (Objects.isNull(limit) || Objects.isNull(offset))
            return super.fromTtoTo(service.getUserContracts(userId));
        else
            return super.fromTtoTo(service.getUserContracts(offset, limit, userId));
    }

    @Override
    ContractTo createTo(Contract contract) {
        return new ContractTo(contract);
    }

    @Override
    Contract convertTOinT(ContractTo contractTo) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return Util.copyFieldToFieldByName(contractTo, Contract.class);
    }
}
