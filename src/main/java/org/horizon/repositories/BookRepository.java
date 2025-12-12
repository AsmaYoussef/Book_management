package org.horizon.repositories;

import org.horizon.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findFirstByCategory(String category);

    // Count inventory for a category
    int countByCategory(String category);

    // Find by ISBN (for delete)
    Optional<Book> findByIsbn(String isbn);

    // Bulk update example: increase price by percent for all books (native JPQL)
    @Modifying
    @Query("UPDATE Book b SET b.price = b.price * (1 + ?1)") // ?1 = fraction (0.10 means +10%)
    int updatePricesByPercent(double fraction);
}
