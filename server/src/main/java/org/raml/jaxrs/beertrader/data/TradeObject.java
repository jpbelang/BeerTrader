package org.raml.jaxrs.beertrader.data;

import org.raml.jaxrs.beertrader.model.Trade;

import javax.persistence.Basic;
import javax.persistence.ManyToOne;
import java.util.Map;

/**
 * Created. There, you have it.
 */
public class TradeObject extends PersistentObject implements Trade {

    @ManyToOne
    private UserObject fromUser;

    @ManyToOne
    private UserObject toUser;

    @ManyToOne
    private BeerObject tradedBeer;

    @Basic
    private int count;

    @Override
    public Map<String, Object> getAdditionalProperties() {
        return null;
    }

    @Override
    public void setAdditionalProperties(String key, Object value) {

    }

    @Override
    public String getFrom() {
        return fromUser.getId();
    }

    @Override
    public void setFrom(String from) {

    }

    @Override
    public String getTo() {
        return toUser.getId();
    }

    @Override
    public void setTo(String to) {

    }

    @Override
    public String getBeer() {
        return tradedBeer.getId();
    }

    @Override
    public void setBeer(String beer) {

    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public void setCount(int count) {

        this.count = count;
    }
}
