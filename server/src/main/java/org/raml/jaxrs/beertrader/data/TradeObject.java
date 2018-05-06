package org.raml.jaxrs.beertrader.data;

import org.raml.jaxrs.beertrader.model.Trade;

import javax.persistence.Basic;
import javax.persistence.ManyToOne;
import java.util.Map;

/**
 * Created. There, you have it.
 */
public class TradeObject extends PersistentObject {

    @ManyToOne
    private UserObject fromUser;

    @ManyToOne
    private UserObject toUser;

    @ManyToOne
    private BeerObject tradedBeer;

    @Basic
    private int count;

    public UserObject getFromUser() {
        return fromUser;
    }

    public void setFromUser(UserObject fromUser) {
        this.fromUser = fromUser;
    }

    public UserObject getToUser() {
        return toUser;
    }

    public void setToUser(UserObject toUser) {
        this.toUser = toUser;
    }

    public BeerObject getTradedBeer() {
        return tradedBeer;
    }

    public void setTradedBeer(BeerObject tradedBeer) {
        this.tradedBeer = tradedBeer;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {

        this.count = count;
    }
}
