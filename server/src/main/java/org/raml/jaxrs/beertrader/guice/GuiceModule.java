package org.raml.jaxrs.beertrader.guice;

import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.ServletModule;

import javax.inject.Inject;

/**
 * Created by Jean-Philippe Belanger on 12/1/17.
 * Just potential zeroes and ones
 */

public class GuiceModule extends ServletModule {

    static public class JPAInitializer {

        private PersistService service;

        @Inject
        public JPAInitializer(PersistService service) {

            this.service = service;

            service.start();
        }

        public void stop() {

            service.stop();
        }
    }


    @Override
    protected void configureServlets() {
        install(new JpaPersistModule("beer-pu"));  // like we saw earlier.
        bind(JPAInitializer.class).asEagerSingleton();
        filter("/*").through(PersistFilter.class);
    }
}
