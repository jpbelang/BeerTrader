package org.raml.jaxrs.beertrader;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceFilter;
import org.raml.jaxrs.beertrader.guice.GuiceModule;
import org.raml.jaxrs.beertrader.resources.ApplicationConfig;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.ServletProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

/**
 * Created by Jean-Philippe Belanger on 12/1/17.
 * Just potential zeroes and ones
 */
public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static  Injector injector;
    public static void main(String[] args) throws Exception {

        // Guice
        injector = Guice.createInjector(new GuiceModule());


        Server server = new Server(9998);
        ServletContextHandler sch = new ServletContextHandler(server, "/");
        sch.addServlet(org.eclipse.jetty.servlet.DefaultServlet.class,"/");

        ServletHolder jerseyServletHolder = new ServletHolder(new ServletContainer());
        jerseyServletHolder.setInitParameter(ServletProperties.JAXRS_APPLICATION_CLASS, ApplicationConfig.class.getCanonicalName());
        sch.addServlet(jerseyServletHolder, "/services/*");
        sch.addFilter(GuiceFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));

        server.start();
        server.join();
    }



}
