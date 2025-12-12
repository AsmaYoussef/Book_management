package org.horizon.services;

import org.horizon.models.Author;
import org.horizon.repositories.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository r) { this.authorRepository = r; }

    public Author save(Author a) { return authorRepository.save(a); }
    public List<Author> findAll() { return authorRepository.findAll(); }
    public Author findById(Long id) {
        return authorRepository.findById(id).orElseThrow(() -> new RuntimeException("Author not found"));
    }
}
