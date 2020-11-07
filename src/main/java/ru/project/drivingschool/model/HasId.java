package ru.project.drivingschool.model;

import org.springframework.util.Assert;

import java.util.Objects;

public interface HasId {

    Long getId();

    default boolean isNew() {
        return Objects.isNull(getId());
    }

    default long id() {
        Assert.notNull(getId(), "Entity must has id");
        return getId();
    }
}
