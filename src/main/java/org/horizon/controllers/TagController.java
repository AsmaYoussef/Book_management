package org.horizon.controllers;

import org.horizon.models.Tag;
import org.horizon.repositories.TagRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagController {

    private final TagRepository repo;

    public TagController(TagRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public Tag create(@RequestBody Tag tag) {
        return repo.save(tag);
    }

    @GetMapping
    public List<Tag> all() {
        return repo.findAll();
    }
}

