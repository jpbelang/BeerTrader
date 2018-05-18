package org.raml.jaxrs.beertrader.data;

import org.raml.jaxrs.beertrader.model.Trade;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Map;

/**
 * Created. There, you have it.
 */
@Entity
public class TradeObject extends PersistentObject {

    @ManyToOne
    private UserObject fromUser;

    @ManyToOne
    private BeerObject fromBeer;

    @Basic
    private int fromCount;

    @ManyToOne
    private UserObject toUser;

    @ManyToOne
    private BeerObject toBeer;

    @Basic
    private int toCount;


    public UserObject getFromUser() {
        return fromUser;
    }

    public void setFromUser(UserObject fromUser) {
        this.fromUser = fromUser;
    }

    public BeerObject getFromBeer() {
        return fromBeer;
    }

    public void setFromBeer(BeerObject fromBeer) {
        this.fromBeer = fromBeer;
    }

    public int getFromCount() {
        return fromCount;
    }

    public void setFromCount(int fromCount) {
        this.fromCount = fromCount;
    }

    public UserObject getToUser() {
        return toUser;
    }

    public void setToUser(UserObject toUser) {
        this.toUser = toUser;
    }

    public BeerObject getToBeer() {
        return toBeer;
    }

    public void setToBeer(BeerObject toBeer) {
        this.toBeer = toBeer;
    }

    public int getToCount() {
        return toCount;
    }

    public void setToCount(int toCount) {
        this.toCount = toCount;
    }
}
