package org.horizon.services;

import org.horizon.DTO.CreateBookRequest;
import org.horizon.models.Author;
import org.horizon.models.Book;
import org.horizon.models.Publisher;
import org.horizon.models.Tag;
import org.horizon.repositories.AuthorRepository;
import org.horizon.repositories.BookRepository;
import org.horizon.repositories.PublisherRepository;
import org.horizon.repositories.TagRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;
    private final TagRepository tagRepository;

    public BookService(BookRepository bookRepository,
                       AuthorRepository authorRepository,
                       PublisherRepository publisherRepository,
                       TagRepository tagRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
        this.tagRepository = tagRepository;
    }

    public Book createBook(CreateBookRequest request) {

        Author author = authorRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author not found"));

        Publisher publisher = publisherRepository.findById(request.getPublisherId())
                .orElseThrow(() -> new RuntimeException("Publisher not found"));

        // create book
        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setIsbn(request.getIsbn());
        book.setPrice(request.getPrice());
        book.setQuantity(request.getQuantity());
        book.setCategory(request.getCategory());
        book.setAuthor(author);
        book.setPublisher(publisher);

        // tags
        if (request.getTagIds() != null && !request.getTagIds().isEmpty()) {
            Set<Tag> tags = new HashSet<>(tagRepository.findAllById(request.getTagIds()));
            book.setTags(tags);
        }

        return bookRepository.save(book);
    }

    public Book findByCategory(String category) {
        return bookRepository.findFirstByCategory(category)
                .orElseThrow(() -> new RuntimeException("No book with that category"));
    }

    public java.util.List<Book> getAll() {
        return bookRepository.findAll();
    }
}
