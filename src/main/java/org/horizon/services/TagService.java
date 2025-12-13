package org.horizon.services;

import org.horizon.DTO.CreateTagRequest;
import org.horizon.DTO.UpdateTagRequest;
import org.horizon.models.Tag;
import org.horizon.repositories.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public Tag create(CreateTagRequest request) {
        Tag tag = new Tag();
        tag.setName(request.getName());
        return tagRepository.save(tag);
    }

    public List<Tag> getAll() {
        return tagRepository.findAll();
    }

    public Tag getById(Long id) {
        return tagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag not found"));
    }

    public Tag update(Long id, UpdateTagRequest request) {
        Tag tag = getById(id);
        tag.setName(request.getName());
        return tagRepository.save(tag);
    }

    public void delete(Long id) {
        tagRepository.deleteById(id);
    }
}
