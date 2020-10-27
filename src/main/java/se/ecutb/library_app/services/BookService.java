package se.ecutb.library_app.services;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import se.ecutb.library_app.entities.Book;
import se.ecutb.library_app.repositories.BookRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    @Cacheable(value = "bookCache")
    public List<Book> findAll(String title) {
        return bookRepository.findAll();
    }

    @Cacheable(value = "bookCache", key = "#result.id")
    public Book create(Book book) {
        return bookRepository.save(book);
    }

    @Cacheable(value = "bookCache", key = "#id")
    public Book update(String id, Book book) {
        return bookRepository.save(book);
    }

    @Cacheable(value = "bookCache", key = "#id")
    public void delete(String id) {
        if(!bookRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Could not find book with id %s", id));
        }
        bookRepository.deleteById(id);
    }
}
