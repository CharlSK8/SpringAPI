package com.platzi.market.domain.repository;

import com.platzi.market.domain.Purchase;
import java.util.List;
import java.util.Optional;

// A contract that defines the methods that the repository must implement.
public interface PurchaseRepository {

    List<Purchase> getAll();
    Optional<List<Purchase>> getByIdClient(String clientId);
    Purchase save(Purchase purchase);

}
