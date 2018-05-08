package org.raml.jaxrs.beertrader;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Created by Jean-Philippe Belanger on 12/1/17.
 * Just potential zeroes and ones
 */
@SpringBootApplication
public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {


        SpringApplication.run(Main.class, args);
    }



}
