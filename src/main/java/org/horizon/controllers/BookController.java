package org.horizon.controllers;

import org.horizon.DTO.CreateBookRequest;
import org.horizon.models.Book;
import org.horizon.services.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@Valid @RequestBody CreateBookRequest request) {
        return ResponseEntity.ok(bookService.createBook(request));
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAll());
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<Book> getFirstByCategory(@PathVariable String category) {
        return ResponseEntity.ok(bookService.findByCategory(category));
    }

    @GetMapping("/inventory")
    public ResponseEntity<Integer> getInventory(@RequestParam String category) {
        return ResponseEntity.ok(bookService.inventory(category));
    }

    @PutMapping("/update-prices")
    public ResponseEntity<String> updatePrices() {
        bookService.updatePricesByPercent(0.10); // 10% increase
        return ResponseEntity.ok("All prices increased by 10%");
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<String> deleteBook(@PathVariable String isbn) {
        bookService.deleteBook(isbn);
        return ResponseEntity.ok("Book deleted: " + isbn);
    }
}
