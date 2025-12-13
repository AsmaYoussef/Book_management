package org.horizon.controllers;

import org.horizon.DTO.CreateTagRequest;
import org.horizon.DTO.UpdateTagRequest;
import org.horizon.models.Tag;
import org.horizon.services.TagService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping
    public ResponseEntity<Tag> create(@RequestBody CreateTagRequest request) {
        return ResponseEntity.ok(tagService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<Tag>> getAll() {
        return ResponseEntity.ok(tagService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tag> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(tagService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tag> update(@PathVariable Long id, @RequestBody UpdateTagRequest request) {
        return ResponseEntity.ok(tagService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        tagService.delete(id);
        return ResponseEntity.ok("Tag deleted");
    }
}
