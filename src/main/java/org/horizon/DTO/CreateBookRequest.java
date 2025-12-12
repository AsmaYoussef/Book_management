package org.horizon.DTO;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.util.List;

@Data
public class CreateBookRequest {

    @NotBlank(message = "Title cannot be blank")
    private String title;

    @NotBlank(message = "ISBN cannot be blank")
    @Pattern(regexp = "^\\d{2}-\\d{3}-\\d{3}$", message = "ISBN must follow xx-xxx-xxx")
    private String isbn;

    @DecimalMin(value = "0.0", message = "Price must be greater than 0")
    private double price;

    @Min(value = 0, message = "Quantity cannot be negative")
    private int quantity;

    @NotBlank(message = "Category cannot be blank")
    private String category;

    @NotNull(message = "Author ID is required")
    private Long authorId;

    @NotNull(message = "Publisher ID is required")
    private Long publisherId;

    private List<Long> tagIds;
}
