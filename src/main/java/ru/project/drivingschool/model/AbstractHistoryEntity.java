package ru.project.drivingschool.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import ru.project.drivingschool.util.DateTimeUtil;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

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
    @ToString.Exclude protected LocalDateTime createdOn;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Employee.class)
    @JoinColumn(name = "created_by", referencedColumnName = "id", updatable = false)
    protected Employee createdBy;

    @Column(name = "changed_on")
    @DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN)
    @ToString.Exclude protected LocalDateTime changedOn;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Employee.class)
    @JoinColumn(name = "changed_by", referencedColumnName = "id")
    protected Employee changedBy;

    @Override
    public String toString() {
        return "AbstractHistoryEntity{" +
                "id=" + id +
                ", createdBy=" + (Objects.isNull(createdBy) ? null : createdBy.id) +
                ", changedBy=" + (Objects.isNull(changedBy) ? null : changedBy.id) +
                '}';
    }
}
