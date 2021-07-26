package ru.project.drivingschool.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import ru.project.drivingschool.model.common.AbstractKeyHistoryEntity;
import ru.project.drivingschool.util.DateTimeUtil;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "contracts")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
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
    protected Set<Contract> child;
}
