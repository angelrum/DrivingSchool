package ru.project.drivingschool.model.common;

import lombok.*;
import ru.project.drivingschool.model.embedded.History;
import javax.persistence.*;
import java.util.Objects;

@MappedSuperclass
@Getter @Setter @ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
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
    protected History history = new History();

    public AbstractKeyHistoryEntity(Long id, History history) {
        super(id);
        this.history = Objects.isNull(history) ? new History() : history;
    }

//    public void setHistory(History history) {
//        this.history = Objects.isNull(history) ? new History() : history;
//    }
}
