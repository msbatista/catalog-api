package com.msb.domain.types;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.msb.domain.base.EntityBase;

@Entity
public class CatalogType extends EntityBase<CatalogType> {
    @Column private String name;

    public CatalogType() {}

    public CatalogType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
