package pl.com.bottega.infrastructure;

import pl.com.bottega.application.LendingRepository;
import pl.com.bottega.model.Lending;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class JPALendingRepository implements LendingRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void put(Lending lending) {
        entityManager.persist(lending);
    }

    @Override
    public String getClientNameOf(Long id) {
        Query query = entityManager.createQuery("SELECT l.clientName FROM Lending l WHERE l.bookId = :id");
        query.setParameter("id", id);
        return (String) query.getSingleResult();
    }

}
