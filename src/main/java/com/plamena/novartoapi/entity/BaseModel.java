package com.plamena.novartoapi.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

@MappedSuperclass
public abstract class BaseModel {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object object) {

        if (object == this) return true;
        if (object == null) return false;
        if (object.getClass() != getClass()) return false;

        BaseModel other = (BaseModel) object;
        if (getId() == null || other.getId() == null) return false;

        return getId().equals(other.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }
}

