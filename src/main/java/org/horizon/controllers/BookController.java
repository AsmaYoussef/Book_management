package org.horizon.controllers;

import jakarta.validation.Valid;
import org.horizon.DTO.CreateBookRequest;
import org.horizon.models.Book;
import org.horizon.services.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @PostMapping
    public Book create(@Valid @RequestBody CreateBookRequest request) {
        return service.createBook(request);
    }

    @GetMapping
    public List<Book> getAll() {
        return service.getAll();
    }

    @GetMapping("/category/{category}")
    public Book getByCategory(@PathVariable String category) {
        return service.findByCategory(category);
    }
}