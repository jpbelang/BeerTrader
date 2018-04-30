package org.raml.jaxrs.beertrader.data;

import org.raml.jaxrs.beertrader.model.Beer;
import org.raml.jaxrs.beertrader.model.BeerType;

import javax.persistence.Basic;
import javax.persistence.Entity;
import java.util.Map;

/**
 * Created. There, you have it.
 */
@Entity
public class BeerObject extends PersistentObject implements Beer {

    @Basic
    private BeerType type;
    private String id;
    private String name;

    @Override
    public Map<String, Object> getAdditionalProperties() {
        return null;
    }

    @Override
    public void setAdditionalProperties(String key, Object value) {

    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {

        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {

        this.name = name;
    }

    @Override
    public BeerType getType() {
        return type;
    }

    @Override
    public void setType(BeerType type) {

        this.type = type;
    }
}
