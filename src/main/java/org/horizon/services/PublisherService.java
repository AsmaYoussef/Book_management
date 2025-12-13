package org.horizon.services;

import org.horizon.DTO.CreatePublisherRequest;
import org.horizon.DTO.UpdatePublisherRequest;
import org.horizon.models.Publisher;
import org.horizon.repositories.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {

    private final PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public Publisher create(CreatePublisherRequest request) {
        Publisher publisher = new Publisher();
        publisher.setName(request.getName());
        return publisherRepository.save(publisher);
    }

    public List<Publisher> getAll() {
        return publisherRepository.findAll();
    }

    public Publisher getById(Long id) {
        return publisherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publisher not found"));
    }

    public Publisher update(Long id, UpdatePublisherRequest request) {
        Publisher publisher = getById(id);
        publisher.setName(request.getName());
        return publisherRepository.save(publisher);
    }

    public void delete(Long id) {
        publisherRepository.deleteById(id);
    }
}
