package org.horizon.controllers;

import org.horizon.models.Author;
import org.horizon.services.AuthorService;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    private final AuthorService service;
    public AuthorController(AuthorService s) { this.service = s; }

    @PostMapping
    public Author create(@RequestBody Author author) {
        // keep simple - no DTO (for exam demo)
        return service.save(author);
    }

    @GetMapping
    public List<Author> getAll() { return service.findAll(); }
}
