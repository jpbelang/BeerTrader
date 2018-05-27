package org.raml.jaxrs.beertrader.resources.impl;

import org.raml.jaxrs.beertrader.data.BeerObject;
import org.raml.jaxrs.beertrader.model.Beer;
import org.raml.jaxrs.beertrader.model.BeerImpl;
import org.raml.jaxrs.beertrader.resources.UsersUserIdBeers;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created. There, you have it.
 */
@Component
@Transactional
public class BeersImpl implements UsersUserIdBeers {

    private final EntityManager context;

    @Inject
    public BeersImpl(EntityManager context) {
        this.context = context;
    }

    @Override
    public GetUsersBeersByUserIdResponse getUsersBeersByUserId(String userId) {

        List<BeerObject> beerObjects = context.createQuery("from BeerObject ", BeerObject.class).getResultList();
        List<Beer> beers = beerObjects.stream().map(BeersImpl::beerObjectToBeer).collect(Collectors.toList());

        return GetUsersBeersByUserIdResponse.respond200WithApplicationJson(beers);
    }

    @Override
    public PostUsersBeersByUserIdResponse postUsersBeersByUserId(String userId, Beer entity) {
        BeerObject beerObject = beerToBeerObject(entity, new BeerObject());
        context.persist(beerObject);

        return PostUsersBeersByUserIdResponse.respond201WithApplicationJson(entity, PostUsersBeersByUserIdResponse.headersFor201().withLocation("beers/" +beerObject.getId()));
    }

    @Override
    public GetUsersBeersByUserIdAndEntryIdResponse getUsersBeersByUserIdAndEntryId(String userId, String entryId) {
        try {
            BeerObject beerObject = context.createQuery("from BeerObject beer where beer.id = :id", BeerObject.class).setParameter("id", userId).getSingleResult();
            return GetUsersBeersByUserIdAndEntryIdResponse.respond200WithApplicationJson(beerObjectToBeer(beerObject));
        } catch (NoResultException e) {

            return GetUsersBeersByUserIdAndEntryIdResponse.respond404();
        }
    }

    @Override
    public DeleteUsersBeersByUserIdAndEntryIdResponse deleteUsersBeersByUserIdAndEntryId(String userId, String entryId) {
        try {
            BeerObject beerObject = context.createQuery("from BeerObject beer where beer.id = :id", BeerObject.class).setParameter("id", userId).getSingleResult();
            context.remove(beerObject);
            return DeleteUsersBeersByUserIdAndEntryIdResponse.respond200();
        } catch (NoResultException e) {

            return DeleteUsersBeersByUserIdAndEntryIdResponse.respond404();
        }
    }

    @Override
    public PutUsersBeersByUserIdAndEntryIdResponse putUsersBeersByUserIdAndEntryId(String userId, String entryId, Beer entity) {
        try {
            BeerObject beerObject = context.createQuery("from BeerObject user where user.id = :id", BeerObject.class).setParameter("id", userId).getSingleResult();
            beerObject = beerToBeerObject(entity, beerObject);
            context.persist(beerObject);
            return PutUsersBeersByUserIdAndEntryIdResponse.respond200();
        } catch (NoResultException e) {

            return PutUsersBeersByUserIdAndEntryIdResponse.respond404();
        }
    }

    private static Beer beerObjectToBeer(BeerObject db) {
        Beer beer = new BeerImpl();
        beer.setName(db.getName());
        beer.setDescription(db.getDescription());
        beer.setType(db.getType());
        beer.setStyle(db.getStyle());


        return beer;
    }

    private static BeerObject beerToBeerObject(Beer restBeer, BeerObject beerObject) {

        beerObject.setName(restBeer.getName());
        beerObject.setType(restBeer.getType());
        beerObject.setStyle(restBeer.getStyle());
        beerObject.setDescription(restBeer.getDescription());
        return beerObject;
    }

}
