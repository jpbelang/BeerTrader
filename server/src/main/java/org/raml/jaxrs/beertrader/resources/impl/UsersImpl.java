package org.raml.jaxrs.beertrader.resources.impl;

import org.raml.jaxrs.beertrader.resources.Users;

/**
 * Created. There, you have it.
 */
public class UsersImpl implements Users {

    @Override
    public GetUsersResponse getUsers() {

        return GetUsersResponse.respond200WithApplicationJson(null);
    }
}
