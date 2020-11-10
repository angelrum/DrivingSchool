package ru.project.drivingschool.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import ru.project.drivingschool.util.DateTimeUtil;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractHistoryEntity implements HasId {
    public static final int START_SEQ = 1_000;

    @Id
    //@SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "created_on", updatable = false)
    @DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN)
    @CreationTimestamp
    protected LocalDateTime createdOn;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Employee.class)
    @JoinColumn(name = "created_by", referencedColumnName = "id", updatable = false)
    protected Employee createdBy;

    @Column(name = "changed_on")
    @DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN)
    protected LocalDateTime changedOn;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Employee.class)
    @JoinColumn(name = "changed_by", referencedColumnName = "id")
    protected Employee changedBy;

}
