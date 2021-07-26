package ru.project.drivingschool.to;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import ru.project.drivingschool.model.Contract;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ContractTo extends BaseTo {

    protected String number;

    protected LocalDate startDate;

    protected LocalDate endDate;

    protected ContractTo parent;

    protected Set<ContractTo> child;

    public ContractTo(Long id, String number, LocalDate startDate, LocalDate endDate, Contract parent, Set<Contract> child) {
        super(id);
        this.number = number;
        this.startDate = startDate;
        this.endDate = endDate;
        this.setParent(parent);
        this.setChild(child);
    }

    public ContractTo(Contract c) {
        this(c.getId(), c.getNumber(), c.getStartDate(), c.getEndDate(), c.getParent(), c.getChild());
    }

    public void setParent(Contract parent) {
        this.parent = Objects.nonNull(parent) ? new ContractTo(parent) : null;
    }

    public void setChild(Set<Contract> child) {
        if (Objects.nonNull(child)) {
            Set<ContractTo> cts = new HashSet<>();
            child.forEach(c -> {
                try {
                    Contract contract = (Contract) c.clone();
                    contract.setChild(null);
                    contract.setParent(null);
                    cts.add(new ContractTo(c));
                } catch (CloneNotSupportedException e) {
                    //ignore
                }
            });
            this.child = cts;
        }
    }
}
