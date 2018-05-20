package org.raml.jaxrs.beertrader.resources.impl;

import org.raml.jaxrs.beertrader.data.UserObject;
import org.raml.jaxrs.beertrader.model.User;
import org.raml.jaxrs.beertrader.model.UserImpl;
import org.raml.jaxrs.beertrader.resources.Users;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created. There, you have it.
 */
@Component
@Transactional
public class UsersImpl implements Users {

    final private EntityManager context;

    @Inject
    public UsersImpl(EntityManager context) {
        this.context = context;
    }

    @Override
    public GetUsersResponse getUsers() {

        List<UserObject> dbUsers = context.createQuery("from UserObject ", UserObject.class).getResultList();
        List<User> users = dbUsers.stream().map(UsersImpl::userObjectToUser).collect(Collectors.toList());

        return GetUsersResponse.respond200WithApplicationJson(users);
    }


    @Override
    public PostUsersResponse postUsers(User entity) {

        UserObject uo = userToUserObject(entity, new UserObject());
        context.persist(uo);

        return PostUsersResponse.respond201WithApplicationJson(userObjectToUser(uo));
    }

    @Override
    public GetUsersByUserIdResponse getUsersByUserId(String userId) {

        try {
            UserObject dbUser = context.createQuery("from UserObject user where user.id = :id", UserObject.class).setParameter("id", userId).getSingleResult();
            return GetUsersByUserIdResponse.respond200WithApplicationJson(userObjectToUser(dbUser));
        } catch (NoResultException e) {

            return GetUsersByUserIdResponse.respond404();
        }
    }

    @Override
    public DeleteUsersByUserIdResponse deleteUsersByUserId(String userId) {

        try {
            UserObject dbUser = context.createQuery("from UserObject user where user.id = :id", UserObject.class).setParameter("id", userId).getSingleResult();
            context.remove(dbUser);
            return DeleteUsersByUserIdResponse.respond200();
        } catch (NoResultException e) {

            return DeleteUsersByUserIdResponse.respond404();
        }
    }

    @Override
    public PutUsersByUserIdResponse putUsersByUserId(String userId, User entity) {
        try {
            UserObject dbUser = context.createQuery("from UserObject user where user.id = :id", UserObject.class).setParameter("id", userId).getSingleResult();
            dbUser = userToUserObject(entity, dbUser);
            context.persist(dbUser);
            return PutUsersByUserIdResponse.respond200();
        } catch (NoResultException e) {

            return PutUsersByUserIdResponse.respond404();
        }
    }

    private static  User userObjectToUser(UserObject db) {
        User user = new UserImpl();
        user.setEmail(db.getEmail());
        user.setName(db.getName());
        user.setId(db.getId());

        return user;
    }

    private static UserObject userToUserObject(User restUser, UserObject uo) {

        uo.setEmail(restUser.getEmail());
        uo.setName(restUser.getName());

        return uo;
    }

}
