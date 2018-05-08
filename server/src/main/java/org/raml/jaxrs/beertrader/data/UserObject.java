package org.raml.jaxrs.beertrader.data;

import org.raml.jaxrs.beertrader.model.User;

import javax.persistence.Basic;
import javax.persistence.Entity;
import java.util.Map;

/**
 * Created. There, you have it.
 */
@Entity
public class UserObject extends PersistentObject {

    @Basic
    private String name;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }
}
