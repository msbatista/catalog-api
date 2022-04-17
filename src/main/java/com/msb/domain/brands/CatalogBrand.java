package com.msb.domain.brands;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.msb.domain.base.EntityBase;

@Entity
public class CatalogBrand extends EntityBase<CatalogBrand> {
    @Column private String name;

    public CatalogBrand(String name) {
        this.name = name;
    }

    public CatalogBrand() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
