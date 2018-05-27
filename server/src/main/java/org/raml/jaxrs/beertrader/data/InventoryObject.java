package org.raml.jaxrs.beertrader.data;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * Created. There, you have it.
 */
@Entity
public class InventoryObject extends PersistentObject {

    @Basic
    private int count;
    private int availableCount;

    @OneToOne
    private BeerObject beer;


    public int getCount() {
        return count;
    }

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
