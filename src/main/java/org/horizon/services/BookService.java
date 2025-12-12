package org.horizon.services;

import org.horizon.DTO.CreateBookRequest;
import org.horizon.models.Author;
import org.horizon.models.Book;
import org.horizon.repositories.AuthorRepository;
import org.horizon.repositories.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    // add other repos (PublisherRepository, TagRepository) if you need them

    // Correct constructor: inject both repos (add other repos as needed)
    public BookService(BookRepository bookRepository,
                       AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    // CREATE
    public Book createBook(CreateBookRequest request) {
        Author author = authorRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author with id " + request.getAuthorId() + " not found"));

        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setCategory(request.getCategory());
        book.setIsbn(request.getIsbn());
        book.setPrice(request.getPrice());
        book.setQuantity(request.getQuantity());
        book.setAuthor(author);

        return bookRepository.save(book);
    }

    // READ all
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    // READ first by category
    public Book findByCategory(String category) {
        return bookRepository.findFirstByCategory(category)
                .orElseThrow(() -> new RuntimeException("No book with that category"));
    }

    // Inventory count
    public int inventory(String category) {
        return bookRepository.countByCategory(category);
    }

    // Bulk update prices (transactional for @Modifying)
    @Transactional
    public int updatePricesByPercent(double fraction) {
        return bookRepository.updatePricesByPercent(fraction);
    }

    // Delete by ISBN
    @Transactional
    public boolean deleteBook(String isbn) {
        return bookRepository.findByIsbn(isbn).map(book -> {
            bookRepository.delete(book);
            return true;
        }).orElse(false);
    }
}
