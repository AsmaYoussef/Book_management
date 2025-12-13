package org.horizon.controllers;

import org.horizon.DTO.CreateAuthorRequest;
import org.horizon.DTO.UpdateAuthorRequest;
import org.horizon.models.Author;
import org.horizon.services.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Author> create(@RequestBody CreateAuthorRequest request) {
        // Pass request to service
        return ResponseEntity.ok(authorService.create(request));
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<Author>> getAll() {
        return ResponseEntity.ok(authorService.getAll());
    }

    // READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<Author> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(authorService.getById(id));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Author> update(@PathVariable Long id, @RequestBody UpdateAuthorRequest request) {
        return ResponseEntity.ok(authorService.update(id, request));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        System.out.println("Trying to delete author with ID: " + id);
        authorService.delete(id);
        return ResponseEntity.ok("Author deleted");
    }

}
