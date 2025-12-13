package org.horizon.controllers;

import org.horizon.DTO.CreatePublisherRequest;
import org.horizon.DTO.UpdatePublisherRequest;
import org.horizon.models.Publisher;
import org.horizon.services.PublisherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publishers")
public class PublisherController {

    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @PostMapping
    public ResponseEntity<Publisher> create(@RequestBody CreatePublisherRequest request) {
        return ResponseEntity.ok(publisherService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<Publisher>> getAll() {
        return ResponseEntity.ok(publisherService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Publisher> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(publisherService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Publisher> update(@PathVariable Long id,
                                            @RequestBody UpdatePublisherRequest request) {
        return ResponseEntity.ok(publisherService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        publisherService.delete(id);
        return ResponseEntity.ok("Publisher deleted");
    }
}
