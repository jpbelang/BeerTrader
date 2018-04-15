package org.raml.jaxrs.beertrader;

import org.raml.jaxrs.beertrader.resources.ApplicationConfig;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.ServletProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Jean-Philippe Belanger on 12/1/17.
 * Just potential zeroes and ones
 */
public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {

        Server server = new Server(9998);
        ServletContextHandler sch = new ServletContextHandler(server, "/");
        sch.addServlet(org.eclipse.jetty.servlet.DefaultServlet.class,"/");

        ServletHolder jerseyServletHolder = new ServletHolder(new ServletContainer());
        jerseyServletHolder.setInitParameter(ServletProperties.JAXRS_APPLICATION_CLASS, ApplicationConfig.class.getCanonicalName());
        sch.addServlet(jerseyServletHolder, "/services/*");

        server.start();
        server.join();
    }



}
