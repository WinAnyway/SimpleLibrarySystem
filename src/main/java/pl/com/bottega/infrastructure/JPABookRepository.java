package pl.com.bottega.infrastructure;

import pl.com.bottega.application.BookQuery;
import pl.com.bottega.application.BookRepository;
import pl.com.bottega.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JPABookRepository implements BookRepository{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void put(Book book) {
        entityManager.persist(book);
    }

    @Override
    public void remove(Long bookId) {
        Query query = entityManager.createQuery("DELETE FROM Book b WHERE b.id = :id");
        query.setParameter("id", bookId);
        query.executeUpdate();
    }

    @Override
    public List<Book> search(BookQuery bookQuery) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = cb.createQuery(Book.class);
        Root<Book> book = criteriaQuery.from(Book.class);
        Set<Predicate> predicates = createPredicates(bookQuery, cb, book);
        criteriaQuery.where(predicates.toArray(new Predicate[]{}));
        Query query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    private Set<Predicate> createPredicates(BookQuery bookQuery, CriteriaBuilder cb, Root<Book> book) {
        Set<Predicate> predicates = new HashSet<>();
        if(bookQuery.getTitle() != null) {
            String likeTitle = "%" + bookQuery.getTitle() + "%";
            predicates.add(cb.like(book.get("title"), likeTitle));
        }
        if(bookQuery.getAuthor() != null) {
            String likeAuthor = "%" + bookQuery.getAuthor() + "%";
            predicates.add(cb.like(book.get("author"), likeAuthor));
        }
        if(bookQuery.getYear() != null)
            predicates.add(cb.equal(book.get("year"), bookQuery.getYear()));
        return predicates;
    }

    @Override
    public Book get(Long bookId) {
        return entityManager.find(Book.class, bookId);
    }

    @Override
    public List<Book> getAllBooks() {
        Query query = entityManager.createQuery("FROM Book b");
        return query.getResultList();
    }

    @Override
    public Long countAvailable() {
        Query query = entityManager.createQuery("SELECT COUNT(b) FROM Book b WHERE b.available = true");
        return (Long) query.getSingleResult();
    }

}
