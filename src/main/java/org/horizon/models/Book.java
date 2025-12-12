package org.horizon.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title cannot be blank")
    @Column(nullable = false)
    private String title;

    @ManyToOne // Many books can belong to one author
    @JoinColumn(name = "author_id", nullable = false)
    @NotNull(message = "Author is required")
    private Author author; // <-- now this is an Author object

    @NotBlank(message = "ISBN cannot be blank")
    @Pattern(regexp = "^\\d{2}-\\d{3}-\\d{3}$", message = "ISBN must be in the format xx-xxx-xxx")
    @Column(unique = true)
    private String isbn;

    @DecimalMin(value = "0.0", message = "Price must be greater than 0")
    @Column(nullable = false)
    private double price;

    @Min(value = 0, message = "Quantity cannot be negative")
    @Column(nullable = false)
    private int quantity;

    @NotBlank(message = "Category cannot be blank")
    @Column(nullable = false)
    private String category;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @ManyToMany
    @JoinTable(
            name = "book_tag",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();
}

