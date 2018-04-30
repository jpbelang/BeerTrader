package org.raml.jaxrs.beertrader.data;

import org.raml.jaxrs.beertrader.model.User;

import javax.persistence.Basic;
import javax.persistence.Entity;
import java.util.Map;

/**
 * Created. There, you have it.
 */
@Entity
public class UserObject extends PersistentObject implements User {

    @Basic
    private String id;
    private String name;
    private String email;

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
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {

        this.email = email;
    }
}
