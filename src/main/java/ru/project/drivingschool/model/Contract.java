package ru.project.drivingschool.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import ru.project.drivingschool.model.common.AbstractKeyHistoryEntity;
import ru.project.drivingschool.util.DateTimeUtil;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "contracts")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Contract extends AbstractKeyHistoryEntity {

    protected String number;

    @Column(name = "start_date")
    @DateTimeFormat(pattern = DateTimeUtil.DATE_PATTERN)
    protected LocalDate startDate;

    @Column(name = "end_date")
    @DateTimeFormat(pattern = DateTimeUtil.DATE_PATTERN)
    protected LocalDate endDate;

    @ManyToOne(targetEntity = Contract.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    protected Contract parent;

    @Transient
    @ToString.Exclude protected Set<Contract> child = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contract)) return false;
        if (!super.equals(o)) return false;
        Contract contract = (Contract) o;
        return number.equals(contract.number) &&
                startDate.equals(contract.startDate) &&
                endDate.equals(contract.endDate) &&
                Objects.equals(parent, contract.parent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), number, startDate, endDate, parent);
    }
}
