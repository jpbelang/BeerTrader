package org.raml.jaxrs.beertrader.resources.impl;

import org.raml.jaxrs.beertrader.data.PersistentObject;
import org.springframework.beans.BeanUtils;

import java.util.function.Supplier;

/**
 * Created. There, you have it.
 */
public class BaseResource<D extends PersistentObject,T> {

    final private Class<?> properties;
    final private Supplier<D> dbObjectSupplier;
    final private Supplier<T> transferObjectSupplier;

    public BaseResource(Class<?> properties, Supplier<D> dbObjectSupplier, Supplier<T> transferObjectSupplier) {
        this.properties = properties;
        this.dbObjectSupplier = dbObjectSupplier;
        this.transferObjectSupplier = transferObjectSupplier;
    }

    protected T dbToTransfer(D db, T transfer) {

        BeanUtils.copyProperties(db, transfer, properties);
        return transfer;
    }

    protected T dbToTransfer(D db) {

        return dbToTransfer(db, transferObjectSupplier.get());
    }

    protected D transferToDB(T transfer) {

        return transferToDB(transfer, dbObjectSupplier.get());
    }

    protected D transferToDB(T transfer, D db) {

        BeanUtils.copyProperties(db, transfer, properties);
        return db;
    }

}
