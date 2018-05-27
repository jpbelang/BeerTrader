package org.raml.jaxrs.beertrader.spring;

import org.glassfish.jersey.server.ResourceConfig;
import org.raml.jaxrs.beertrader.resources.impl.BeersImpl;
import org.raml.jaxrs.beertrader.resources.impl.InventoryImpl;
import org.raml.jaxrs.beertrader.resources.impl.TradesImpl;
import org.raml.jaxrs.beertrader.resources.impl.UsersImpl;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

/**
 * Created. There, you have it.
 */
@Component
@ApplicationPath("/services")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(BeersImpl.class);
        register(InventoryImpl.class);
        register(TradesImpl.class);
        register(UsersImpl.class);
    }
}
