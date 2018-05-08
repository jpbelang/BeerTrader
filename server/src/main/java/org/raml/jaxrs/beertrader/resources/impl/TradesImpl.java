package org.raml.jaxrs.beertrader.resources.impl;

import org.raml.jaxrs.beertrader.data.BeerObject;
import org.raml.jaxrs.beertrader.data.TradeObject;
import org.raml.jaxrs.beertrader.model.Beer;
import org.raml.jaxrs.beertrader.model.Trade;
import org.raml.jaxrs.beertrader.model.TradeImpl;
import org.raml.jaxrs.beertrader.resources.UsersUserIdTrades;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created. There, you have it.
 */
@Component
public class TradesImpl implements UsersUserIdTrades {

    @Inject
    private EntityManager context;


    @Override
    public GetUsersTradesByUserIdResponse getUsersTradesByUserId(String userId) {
        List<TradeObject> tradeObject = context.createQuery("from TradeObject", TradeObject.class).getResultList();
        List<Trade> beers = tradeObject.stream().map(TradesImpl::tradeObjectToTrade).collect(Collectors.toList());

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
            TradeObject tradeObject = context.createQuery("from TradeObject user where user.id = :id", TradeObject.class).setParameter("id", userId).getSingleResult();
            tradeObject = tradeToTradeObject(entity, tradeObject);
            context.persist(tradeObject);
            return PutUsersTradesByUserIdAndTradeIdResponse.respond200();
        } catch (NoResultException e) {

            return PutUsersTradesByUserIdAndTradeIdResponse.respond404();
        }
    }

    private static Trade tradeObjectToTrade(TradeObject db) {
        Trade beer = new TradeImpl();
        beer.setCount(db.getCount());

        return beer;
    }

    private static TradeObject tradeToTradeObject(Trade trade, TradeObject tradeObject) {

        tradeObject.setCount(trade.getCount());
        return tradeObject;
    }

}
