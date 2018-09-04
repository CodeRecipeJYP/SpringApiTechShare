package com.navercorp.bookserver;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;


@Service
public class BookService {
    private ConcurrentMap<Integer, Book> repo = new ConcurrentHashMap<>();
    private AtomicInteger maxId = new AtomicInteger(0);

    public Book findById(Integer id) {
        Book book = repo.get(id);
        Objects.requireNonNull(book, "cannot find book with id: " + id);
        return book;
    }

    public boolean update(Book book) {
        Book old = repo.put(book.getId(), book);
        return old != null;
    }

    public Book create(Book book) {
        Integer id = maxId.addAndGet(1);
        book.setId(id);
        repo.put(id, book);
        return book;
    }

    public boolean delete(Integer id) {
        Book old = repo.remove(id);
        return old != null;
    }
    public Collection<Book> findAll() {
        return repo.values();
    }
}
