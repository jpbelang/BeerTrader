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
public class BeerObject extends PersistentObject {

    @Basic
    private BeerType type;
    private String name;
    private String description;


    public String getName() {
        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public BeerType getType() {
        return type;
    }

    public void setType(BeerType type) {

        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
