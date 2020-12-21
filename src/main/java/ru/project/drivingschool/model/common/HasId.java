package ru.project.drivingschool.model.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.util.Assert;

import java.util.Objects;

public interface HasId {

    Long getId();

    void setId(Long id);

    @JsonIgnore
    default boolean isNew() {
        return Objects.isNull(getId());
    }

    default long id() {
        Assert.notNull(getId(), "Entity must has id");
        return getId();
    }
}
