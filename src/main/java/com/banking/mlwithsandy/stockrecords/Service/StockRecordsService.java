package com.banking.mlwithsandy.stockrecords.Service;

public interface StockRecordsService<T> {
    public T save(T t);
    public T getById(Long id);
}
