package org.raml.jaxrs.beertrader.resources.impl;

import org.raml.jaxrs.beertrader.data.UserObject;
import org.raml.jaxrs.beertrader.model.User;
import org.raml.jaxrs.beertrader.resources.Users;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;

/**
 * Created. There, you have it.
 */
public class UsersImpl implements Users {

    @Inject
    private EntityManager context;

    @Override
    @Transactional
    public GetUsersResponse getUsers() {

        ArrayList<org.raml.jaxrs.beertrader.model.User> list = new ArrayList<>();
        return GetUsersResponse.respond200WithApplicationJson(list);
    }

    @Override
    public PostUsersResponse postUsers(User entity) {
        return null;
    }

    @Override
    public GetUsersByUserIdResponse getUsersByUserId(String userId) {
        return null;
    }

    @Override
    public void putUsersByUserId(String userId, User entity) {

    }

    @Override
    public void deleteUsersByUserId(String userId) {

    }
}
