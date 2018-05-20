package org.raml.jaxrs.beertrader.resources.impl;

import org.raml.jaxrs.beertrader.data.BeerObject;
import org.raml.jaxrs.beertrader.data.TradeObject;
import org.raml.jaxrs.beertrader.data.UserObject;
import org.raml.jaxrs.beertrader.model.Trade;
import org.raml.jaxrs.beertrader.model.TradeImpl;
import org.raml.jaxrs.beertrader.model.TradeProperties;
import org.raml.jaxrs.beertrader.resources.UsersUserIdTrades;
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
public class TradesImpl extends BaseResource<TradeObject, Trade> implements UsersUserIdTrades {

    private final EntityManager context;

    @Inject
    public TradesImpl(EntityManager context) {
        super(TradeProperties.class, TradeObject::new, TradeImpl::new);
        this.context = context;
    }


    @Override
    public GetUsersTradesByUserIdResponse getUsersTradesByUserId(String userId) {
        List<TradeObject> tradeObject = context.createQuery("from TradeObject", TradeObject.class).getResultList();
        List<Trade> beers = tradeObject.stream().map(this::tradeObjectToTrade).collect(Collectors.toList());

        return GetUsersTradesByUserIdResponse.respond200WithApplicationJson(beers);
    }

    @Override
    public PostUsersTradesByUserIdResponse postUsersTradesByUserId(String userId, Trade entity) {
        TradeObject tradeObject = tradeToTradeObject(entity, new TradeObject());
        context.persist(tradeObject);

        return PostUsersTradesByUserIdResponse.respond201WithApplicationJson(entity);
    }

    @Override
    public GetUsersTradesByUserIdAndTradeIdResponse getUsersTradesByUserIdAndTradeId(String userId, String tradeId) {
        try {
            TradeObject tradeObject = context.createQuery("from TradeObject trade where trade.id = :id", TradeObject.class).setParameter("id", userId).getSingleResult();
            return GetUsersTradesByUserIdAndTradeIdResponse.respond200WithApplicationJson(tradeObjectToTrade(tradeObject));
        } catch (NoResultException e) {

            return GetUsersTradesByUserIdAndTradeIdResponse.respond404();
        }
    }

    @Override
    public DeleteUsersTradesByUserIdAndTradeIdResponse deleteUsersTradesByUserIdAndTradeId(String userId, String tradeId) {
        try {
            TradeObject beerObject = context.createQuery("from TradeObject trade where trade.id = :id", TradeObject.class).setParameter("id", userId).getSingleResult();
            context.remove(beerObject);
            return DeleteUsersTradesByUserIdAndTradeIdResponse.respond200();
        } catch (NoResultException e) {

            return DeleteUsersTradesByUserIdAndTradeIdResponse.respond404();
        }
    }

    @Override
    public PutUsersTradesByUserIdAndTradeIdResponse putUsersTradesByUserIdAndTradeId(String userId, String tradeId, Trade entity) {
        try {
            TradeObject tradeObject = context.createQuery("from TradeObject trade where trade.id = :id", TradeObject.class).setParameter("id", userId).getSingleResult();
            tradeObject = tradeToTradeObject(entity, tradeObject);
            context.persist(tradeObject);
            return PutUsersTradesByUserIdAndTradeIdResponse.respond200();
        } catch (NoResultException e) {

            return PutUsersTradesByUserIdAndTradeIdResponse.respond404();
        }
    }

    private  Trade tradeObjectToTrade(TradeObject db) {
        Trade trade = dbToTransfer(db);

        trade.setFromUserReference(db.getFromUser().getId());
        trade.setFromBeerReference(db.getFromBeer().getId());

        trade.setToUserReference(db.getFromUser().getId());
        trade.setToBeerReference(db.getFromBeer().getId());
        
        return trade;
    }

    private TradeObject tradeToTradeObject(Trade trade, TradeObject tradeObject) {

        transferToDB(trade, tradeObject);

        BeerObject fromBeer = context.createQuery("from BeerObject beer where beer.id = :id", BeerObject.class).setParameter("id", tradeObject.getFromBeer()).getSingleResult();
        UserObject fromUser = context.createQuery("from UserObject user where user.id = :id", UserObject.class).setParameter("id", tradeObject.getFromUser()).getSingleResult();
        trade.setFromBeerReference(fromBeer.getId());
        trade.setFromUserReference(fromUser.getId());

        BeerObject toBeer = context.createQuery("from BeerObject beer where beer.id = :id", BeerObject.class).setParameter("id", tradeObject.getToBeer()).getSingleResult();
        UserObject toUser = context.createQuery("from UserObject user where user.id = :id", UserObject.class).setParameter("id", tradeObject.getToUser()).getSingleResult();
        trade.setToBeerReference(toBeer.getId());
        trade.setToUserReference(toUser.getId());

        return tradeObject;
    }

}
