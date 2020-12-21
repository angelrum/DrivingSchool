package ru.project.drivingschool.model.common;

import lombok.*;
import ru.project.drivingschool.model.embedded.History;
import javax.persistence.*;
import java.util.Objects;

@MappedSuperclass
@Getter @Setter @ToString
@NoArgsConstructor
public abstract class AbstractKeyHistoryEntity extends AbstractKeyEntity {

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
    @ToString.Exclude protected History history;

    public AbstractKeyHistoryEntity(Long id, History history) {
        super(id);
        setHistory(history);
    }

    public void setHistory(History history) {
        this.history = Objects.isNull(history) ? new History() : history;
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

}
