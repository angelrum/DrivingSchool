package ru.project.drivingschool.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import ru.project.drivingschool.util.DateTimeUtil;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractHistoryEntity implements HasId {

    @Id
    protected Long id;

    @Column(name = "created_on")
    @DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN)
    protected LocalDateTime createdOn;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Employee.class)
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    protected Employee createdBy;

    @Column(name = "changed_on")
    @DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN)
    protected LocalDateTime changedOn;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Employee.class)
    @JoinColumn(name = "changed_by", referencedColumnName = "id")
    protected Employee changedBy;

}
