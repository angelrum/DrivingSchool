package ru.project.drivingschool.to;

import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.project.drivingschool.model.common.HasId;

@Setter
@NoArgsConstructor
public class BaseTo implements HasId {

    private Long id;

    public BaseTo(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }
}
