package com.msb.infrastructure.repository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import com.msb.domain.catalogs.CatalogItem;
import com.msb.domain.catalogs.CatalogItemRepository;

import io.quarkus.hibernate.reactive.panache.PanacheQuery;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;

@ApplicationScoped
public class CatalogRepositoryIml implements CatalogItemRepository, PanacheRepository<CatalogItem> {

    @Override
    @Transactional public Uni<CatalogItem> save(CatalogItem item) {
        return persistAndFlush(item);
    }

    @Override
    public PanacheQuery<CatalogItem> findItemsByBrand(Long id, int pageIndex, int pageSize) {
        return find("brandId", id).page(pageIndex, pageSize);
    }

    @Override
    public PanacheQuery<CatalogItem> findItemsByType(Long id, int pageIndex, int pageSize) {
        return find("typeId", id).page(pageIndex, pageSize);
    }

    @Override
    @Transactional public Uni<Boolean> delete(Long id) {
        return deleteById(id);
    }

    @Override
    public PanacheQuery<CatalogItem> findItemsByName(String name, int pageIndex, int pageSize) {
        return find("unaccent(name) LIKE ('%' || unaccent(?1) || '%')", name).page(pageIndex, pageSize);
    }
}
