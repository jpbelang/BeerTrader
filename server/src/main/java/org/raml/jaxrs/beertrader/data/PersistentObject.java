package org.raml.jaxrs.beertrader.data;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Objects;
import java.util.UUID;

/**
 * Created. There, you have it.
 */
@MappedSuperclass
public class PersistentObject {

    @Id
    private String id = UUID.randomUUID().toString();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersistentObject that = (PersistentObject) o;
        return Objects.equals(id, that.id);
    }

    public String getId() {
        return id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
