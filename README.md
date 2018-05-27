# The BeerTrader RAML example program.

This is an example server build using the [RAML-For-JaxRS](https://github.com/mulesoft-labs/raml-for-jax-rs) project.

The written tutorial lives here (insert tutorial url from Medium).  This is the code that the tutorial refers to.

## Building the server

From the root directory:
```
mvn clean install
```

## Running the server

From the root directory:
```
java -jar server/target/server-1.0-SNAPSHOT.jar
```

The project is a normal spring boot project.  Running it inside your IDE should not be a problem.  The main class
is in the `server` submodule and called `org.raml.jaxrs.beertrader.Main`

It will start on port 8080.