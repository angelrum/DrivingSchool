package ru.project.drivingschool.model.embedded;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@MappedSuperclass
@NoArgsConstructor
@Getter @Setter
public class AbstractHistoryEmbedded {

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

    public AbstractHistoryEmbedded(History history) {
        setHistory(history);
    }

    public void setHistory(History history) {
        this.history = Objects.isNull(history) ? new History() : history;
    }
}
