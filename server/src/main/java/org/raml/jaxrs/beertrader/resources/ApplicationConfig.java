package org.raml.jaxrs.beertrader.resources;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.raml.jaxrs.beertrader.guice.GuiceModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spi.Container;
import org.glassfish.jersey.server.spi.ContainerLifecycleListener;
import org.jvnet.hk2.guice.bridge.api.GuiceBridge;
import org.jvnet.hk2.guice.bridge.api.GuiceIntoHK2Bridge;
import org.raml.jaxrs.beertrader.model.BeerImpl;
import org.raml.jaxrs.beertrader.resources.impl.InventoryImpl;
import org.raml.jaxrs.beertrader.resources.impl.UsersImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Jean-Philippe Belanger on 12/1/17.
 * Just potential zeroes and ones
 */
@ApplicationPath("/services")
public class ApplicationConfig extends ResourceConfig {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    public ApplicationConfig(ServiceLocator serviceLocator) {

        registerClasses(UsersImpl.class);
        registerClasses(BeerImpl.class);
        registerClasses(InventoryImpl.class);

        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();

        // Guice
        Injector injector = Guice.createInjector(new GuiceModule());

        GuiceBridge.getGuiceBridge().initializeGuiceBridge(serviceLocator);
        GuiceIntoHK2Bridge bridge = serviceLocator.getService(GuiceIntoHK2Bridge.class);
        bridge.bridgeGuiceInjector(injector);

        logger.debug("'{}' initialized", ApplicationConfig.this.getClass().getName());
    }


}

