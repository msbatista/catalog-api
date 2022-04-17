package com.msb.domain.catalogs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.msb.domain.CatalogDomainException;
import com.msb.domain.base.EntityBase;
import com.msb.domain.brands.CatalogBrand;
import com.msb.domain.types.CatalogType;

@Entity
public final class CatalogItem extends EntityBase<CatalogItem> {
    @Column private String name;
    @Column private String description;
    @Column private double price;
    @Column private int availableStock;
    @Column private String pictureFileName;
    @Column private String pictureUri;
    @OneToOne @JoinColumn(name = "typeId", referencedColumnName="id") private CatalogType catalogType;
    @OneToOne @JoinColumn(name = "brandId", referencedColumnName="id") private CatalogBrand catalogBrand;
    @Column private int restockThreshold;
    @Column private int maxStockThreshold;
    @Column private boolean onReorder;

    public CatalogItem() {
    }

    public CatalogItem(
            String name, 
            String description, 
            double price, 
            int availableStock, 
            String pictureFileName,
            String pictureUri, 
            int restockThreshold, 
            int maxStockThreshold, 
            boolean onReorder) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.availableStock = availableStock;
        this.pictureFileName = pictureFileName;
        this.pictureUri = pictureUri;
        this.restockThreshold = restockThreshold;
        this.maxStockThreshold = maxStockThreshold;
        this.onReorder = onReorder;
    }

    /**
     * Decrements the quantity of a particular item from the stock.
     * 
     * @param quantityToBeRemoved the quantity to be removed.
     * @return the removed quantity.
     * @throws CatalogDomainException
     */
    public int removeStock(int quantityToBeRemoved) throws CatalogDomainException {

        if (this.availableStock == 0) {
            throw new CatalogDomainException(String.format("Empty stock, item '%s' is sold out.", this.name));
        }

        if (quantityToBeRemoved <= 0) {
            throw new CatalogDomainException("Item quantity should be greater than zero.");
        }

        int removed = Math.min(quantityToBeRemoved, this.availableStock);

        this.availableStock -= removed;

        return removed;
    }

    /**
     * Increments the quantity of a item in inventory.
     * 
     * @param quantity int: the quantity to added to the inventory.
     * @return int: the quantity that has been added to the inventory.
     */
    public int addStock(int quantity) {
        int original = this.availableStock;

        if ((this.availableStock + quantity) > this.maxStockThreshold) {
            this.availableStock += (this.maxStockThreshold - this.availableStock);
        } else {
            this.availableStock += quantity;
        }

        this.onReorder = false;

        return this.availableStock - original;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAvailableStock() {
        return availableStock;
    }

    public void setAvailableStock(int availableStock) {
        this.availableStock = availableStock;
    }

    public String getPictureFileName() {
        return pictureFileName;
    }

    public void setPictureFileName(String pictureFileName) {
        this.pictureFileName = pictureFileName;
    }

    public String getPictureUri() {
        return pictureUri;
    }

    public void setPictureUri(String pictureUri) {
        this.pictureUri = pictureUri;
    }

    public CatalogType getCatalogType() {
        return catalogType;
    }

    public void setCatalogType(CatalogType catalogType) {
        this.catalogType = catalogType;
    }

    public CatalogBrand getCatalogBrand() {
        return catalogBrand;
    }

    public void setCatalogBrand(CatalogBrand catalogBrand) {
        this.catalogBrand = catalogBrand;
    }

    public int getRestockThreshold() {
        return restockThreshold;
    }

    public void setRestockThreshold(int restockThreshold) {
        this.restockThreshold = restockThreshold;
    }

    public int getMaxStockThreshold() {
        return maxStockThreshold;
    }

    public void setMaxStockThreshold(int maxStockThreshold) {
        this.maxStockThreshold = maxStockThreshold;
    }

    public boolean isOnReorder() {
        return onReorder;
    }

    public void setOnReorder(boolean onReorder) {
        this.onReorder = onReorder;
    }
}
