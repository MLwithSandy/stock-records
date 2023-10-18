package com.banking.mlwithsandy.stockrecords.Service;

import java.security.Principal;
import java.util.List;

public interface StockRecordsService<T> {
    public T save(T t, Principal principal);
    public T getById(String id, Principal principal);

    public List<T> search(T t, Principal principal);
}
