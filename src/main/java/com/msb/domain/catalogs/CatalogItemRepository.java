package com.msb.domain.catalogs;

import io.quarkus.hibernate.reactive.panache.PanacheQuery;
import io.smallrye.mutiny.Uni;

public interface CatalogItemRepository {
    Uni<CatalogItem> save(CatalogItem item);
    PanacheQuery<CatalogItem> findItemsByName(String name, int pageIndex, int pageSize);
    PanacheQuery<CatalogItem> findItemsByBrand(Long id, int pageIndex, int pageSize);
    PanacheQuery<CatalogItem> findItemsByType(Long id, int pageIndex, int pageSize);
    Uni<Boolean> delete(Long id);
}
