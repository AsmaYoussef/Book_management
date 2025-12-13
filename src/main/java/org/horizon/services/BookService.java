package org.horizon.services;

import org.horizon.DTO.CreateBookRequest;
import org.horizon.models.Author;
import org.horizon.models.Book;
import org.horizon.models.Publisher;
import org.horizon.repositories.AuthorRepository;
import org.horizon.repositories.BookRepository;
import org.horizon.repositories.PublisherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository; // add this

    // Constructor
    public BookService(BookRepository bookRepository,
                       AuthorRepository authorRepository,
                       PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
    }

    // CREATE
    public Book createBook(CreateBookRequest request) {
        // Fetch author
        Author author = authorRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author with id " + request.getAuthorId() + " not found"));

        // Fetch publisher
        Publisher publisher = publisherRepository.findById(request.getPublisherId())
                .orElseThrow(() -> new RuntimeException("Publisher with id " + request.getPublisherId() + " not found"));

        // Create book
        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setCategory(request.getCategory());
        book.setIsbn(request.getIsbn());
        book.setPrice(request.getPrice());
        book.setQuantity(request.getQuantity());
        book.setAuthor(author);
        book.setPublisher(publisher); // <-- assign publisher

        return bookRepository.save(book);
    }

    // Other methods remain the same...
}
