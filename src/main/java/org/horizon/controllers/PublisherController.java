package org.horizon.controllers;

import org.horizon.models.Publisher;
import org.horizon.repositories.PublisherRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publishers")
public class PublisherController {

    private final PublisherRepository repo;

    public PublisherController(PublisherRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public Publisher create(@RequestBody Publisher p) {
        return repo.save(p);
    }

    @GetMapping
    public List<Publisher> all() {
        return repo.findAll();
    }
}
