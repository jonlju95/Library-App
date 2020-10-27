package se.ecutb.library_app.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import se.ecutb.library_app.entities.Book;
import se.ecutb.library_app.services.BookService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> findAllBooks(@RequestParam(required = false) String title) {
        return ResponseEntity.ok(bookService.findAll(title));
    }

    @PostMapping
    public ResponseEntity<Book> saveBook(@Validated @RequestBody Book book) {
        return ResponseEntity.ok(bookService.create(book));
    }
}