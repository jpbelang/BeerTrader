package org.raml.jaxrs.beertrader.resources.impl;

import org.raml.jaxrs.beertrader.data.UserObject;
import org.raml.jaxrs.beertrader.model.User;
import org.raml.jaxrs.beertrader.model.UserImpl;
import org.raml.jaxrs.beertrader.resources.Users;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created. There, you have it.
 */
public class UsersImpl implements Users {

    @Inject
    private EntityManager context;

    @Override
    @Transactional
    public GetUsersResponse getUsers() {

        List<UserObject> dbUsers = context.createQuery("from UserObject ", UserObject.class).getResultList();
        List<User> users = dbUsers.stream().map(UsersImpl::userToUserObject).collect(Collectors.toList());

        return GetUsersResponse.respond200WithApplicationJson(users);
    }


    @Override
    public PostUsersResponse postUsers(User entity) {

        UserObject uo = userToUserObject(entity);
        context.persist(uo);

        return PostUsersResponse.respond201WithApplicationJson(entity);
    }

    @Override
    public GetUsersByUserIdResponse getUsersByUserId(String userId) {
        return GetUsersByUserIdResponse.respond200WithApplicationJson();
    }

    @Override
    public DeleteUsersByUserIdResponse deleteUsersByUserId(String userId) {
        return null;
    }

    @Override
    public PutUsersByUserIdResponse putUsersByUserId(String userId, User entity) {
        return null;
    }

    private static  User userToUserObject(UserObject db) {
        User user = new UserImpl();
        user.setEmail(db.getEmail());
        user.setName(db.getName());
        user.setId(db.getId());

        return user;
    }

    private static UserObject userToUserObject(User restUser) {

        UserObject uo = new UserObject();
        uo.setEmail(restUser.getEmail());
        uo.setName(restUser.getName());

        return uo;
    }

}
