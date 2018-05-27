package org.raml.jaxrs.beertrader.data;

import org.raml.jaxrs.beertrader.model.Beer;
import org.raml.jaxrs.beertrader.model.BeerType;
import org.raml.jaxrs.beertrader.model.InventoryEntryProperties;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * Created. There, you have it.
 */
@Entity
public class InventoryObject extends PersistentObject implements InventoryEntryProperties {

    @Basic
    private int count;
    private int availableCount;

    @Basic
    private int availableCount;

    @OneToOne
    private BeerObject beer;

    @Override
    public int getAvailableCount() {
        return availableCount;
    }

    @Override
    public void setAvailableCount(int count) {
        this.availableCount = count;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public void setCount(int count) {
        this.count = count;
    }

    public BeerObject getBeer() {
        return beer;
    }

    public void setBeer(BeerObject beer) {
        this.beer = beer;
    }

    public int getAvailableCount() {
        return availableCount;
    }

    public void setAvailableCount(int availableCount) {
        this.availableCount = availableCount;
    }
}
