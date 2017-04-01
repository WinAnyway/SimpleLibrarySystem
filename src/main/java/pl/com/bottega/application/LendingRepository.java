package pl.com.bottega.application;

import pl.com.bottega.model.Lending;

public interface LendingRepository {

    void put(Lending lending);

    String getClientNameOf(Long id);
}
