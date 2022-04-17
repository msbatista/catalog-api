package com.msb.domain.base;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * All entities inherit from entity base. And all have id. Id's are immutable.
 */
@MappedSuperclass
public abstract class EntityBase<T extends EntityBase<T>> {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    /**
     * Get object id.
     * @return id.
     */
    public Long getId() {
        return id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof EntityBase) {
            final EntityBase<?> other = (EntityBase<?>) obj;

            return this.id.equals(other.id);
        }

        return false;
    }
}
