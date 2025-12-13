package org.horizon.services;

import org.horizon.DTO.CreateAuthorRequest;
import org.horizon.DTO.UpdateAuthorRequest;
import org.horizon.models.Author;
import org.horizon.repositories.AuthorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    // CREATE a new author
    public Author create(CreateAuthorRequest request) {
        Author author = new Author();
        author.setName(request.getName());
        author.setEmail(request.getEmail());
        return authorRepository.save(author);
    }

    // READ all authors
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    // READ one author by ID
    public Author getById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + id));
    }

    // UPDATE author
    @Transactional
    public Author update(Long id, UpdateAuthorRequest request) {
        Author author = getById(id);

        if (request.getName() != null && !request.getName().isBlank()) {
            author.setName(request.getName());
        }

        if (request.getEmail() != null && !request.getEmail().isBlank()) {
            author.setEmail(request.getEmail());
        }

        return authorRepository.save(author);
    }

    // DELETE author
    @Transactional
    public void delete(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new RuntimeException("Author not found with id: " + id);
        }
        authorRepository.deleteById(id);
    }
}
