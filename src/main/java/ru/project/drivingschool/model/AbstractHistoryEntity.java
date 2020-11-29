package ru.project.drivingschool.model;

import lombok.*;
import ru.project.drivingschool.model.embedded.History;
import javax.persistence.*;
import java.util.Objects;

@MappedSuperclass
@Getter @Setter
public abstract class AbstractHistoryEntity {

    @Embedded
    @AttributeOverrides(value = {
            @AttributeOverride(name = "createdOn",
                    column = @Column(name = "created_on", updatable = false)),
            @AttributeOverride(name = "changedOn",
                    column = @Column(name = "changed_on"))
    })
    @AssociationOverrides({
            @AssociationOverride(name = "createdBy",
                    joinColumns = @JoinColumn(name = "created_by", referencedColumnName = "id", updatable = false)),
            @AssociationOverride(name = "changedBy",
                    joinColumns = @JoinColumn(name = "changed_by", referencedColumnName = "id"))
    })
    protected History history;

    AbstractHistoryEntity(History history) {
        this.history = Objects.isNull(history) ? new History() : history;
    }

    public AbstractHistoryEntity() {
        this.history = new History();
    }

    //    @Column(name = "created_on", updatable = false)
//    @DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN)
//    @CreationTimestamp
//    protected LocalDateTime createdOn;
//
//    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Employee.class)
//    @JoinColumn(name = "created_by", referencedColumnName = "id", updatable = false)
//    protected Employee createdBy;
//
//    @Column(name = "changed_on")
//    @DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN)
//    protected LocalDateTime changedOn;
//
//    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Employee.class)
//    @JoinColumn(name = "changed_by", referencedColumnName = "id")
//    protected Employee changedBy;

    @Override
    public String toString() {
        return "AbstractHistoryEntity{" +
                ", createdBy=" + (Objects.isNull(history.getCreatedBy()) ? null : history.getCreatedBy().id) +
                ", changedBy=" + (Objects.isNull(history.getChangedBy()) ? null : history.getChangedBy().id) +
                '}';
    }
}
